package com.tazz.staffutilsbungee.helpop;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.CooldownUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HelpOpCommand extends Command {

    public HelpOpCommand() {
        super("helpop", "", "ticket");
        CooldownUtils.createCooldown("Help");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to execute this command."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /helpop <message>"));
            return;
        }

        if (CooldownUtils.isOnCooldown("Help", player)) {
            sender.sendMessage(Utils.c("&cYou are on cooldown for another " + CooldownUtils.getCooldown("Help", player) + "seconds!"));
            return;
        }

        StringBuilder reason = new StringBuilder();
        for (String arg : args) {
            reason.append(arg).append(" ");
        }
        sender.sendMessage(Utils.c("&aYou have successfully requested assistance!"));

        CooldownUtils.addCooldown("Help", (ProxiedPlayer) sender, 60);

        StaffUtils.getInstance().getHelpOpManager().helpopMessage((ProxiedPlayer) sender, reason.toString());
    }
}
