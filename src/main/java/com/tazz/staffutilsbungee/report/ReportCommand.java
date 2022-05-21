package com.tazz.staffutilsbungee.report;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.CooldownUtils;
import com.tazz.staffutilsbungee.utils.Prefix;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {

    public ReportCommand() {
        super("report", "", "");
        CooldownUtils.createCooldown("Report");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to execute this command."));
        }
        if (args.length < 2) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /report <player> <reason>"));
            return;
        }
        ProxiedPlayer reporter = (ProxiedPlayer) sender;
        ProxiedPlayer reported = ProxyServer.getInstance().getPlayer(args[0]);
        if (reported == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "That player does not exist."));
            return;
        }

        if (reported.equals(reporter)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You may not report yourself."));
            return;
        }

        if (CooldownUtils.isOnCooldown("Report", reporter)) {
            sender.sendMessage(Utils.c("&cYou are on cooldown for another " + CooldownUtils.getCooldown("Report", reporter) + " seconds!"));
            return;
        }

        StringBuilder b = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            b.append(args[i]).append(" ");
        }
        String reason = b.toString();

        sender.sendMessage(Utils.c("&aReport received. Thanks!"));

        CooldownUtils.addCooldown("Report", reporter, 60);

        StaffUtils.getInstance().getReportManager().reportMessage(reporter, reported, reason);
    }
}
