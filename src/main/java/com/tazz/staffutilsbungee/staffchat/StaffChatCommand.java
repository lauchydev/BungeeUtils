package com.tazz.staffutilsbungee.staffchat;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

public class StaffChatCommand extends Command {

    public static List<ProxiedPlayer> staffChat = new ArrayList<ProxiedPlayer>();

    public StaffChatCommand() {
        super("staffchat", "seabot.basic.staff", "sc");
    }

    public void toggleStaffChat(ProxiedPlayer p){
        if (staffChat.contains(p)) staffChat.remove(p);
        else staffChat.add(p);

        p.sendMessage(Utils.c("&aYou have toggled staff chat " + (staffChat.contains(p) ? "on" : "off") + "."));

    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to execute this command."));
            return;
        }

        if (!sender.hasPermission("seabot.basic.staff")) {
            sender.sendMessage(ChatColor.RED + "You must be helper or higher to use this command.");
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length == 0) {
            toggleStaffChat(p);
            return;
        }

        StringBuilder b = new StringBuilder();
        for (String arg : args) {
            b.append(arg);
            b.append(" ");
        }
        String message = b.toString();

        StaffUtils.getInstance().getStaffChatManager().staffChatMessage(p, message);
    }
}
