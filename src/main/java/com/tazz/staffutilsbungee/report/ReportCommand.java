package com.tazz.staffutilsbungee.report;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.CooldownUtils;
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
        if (args.length == 0) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /report <player> <reason>"));
            return;
        }
        if (args.length == 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /report <player> <reason>"));
            return;
        }
        ProxiedPlayer reported = ProxyServer.getInstance().getPlayer(args[0]);
        if (reported == (ProxiedPlayer) sender) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You may not report yourself."));
            return;
        }
        if (reported == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "That player does not exist."));
            return;
        }
        if (CooldownUtils.isOnCooldown("Report", (ProxiedPlayer) sender)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You are on cooldown for another " + ChatColor.DARK_RED +
                    CooldownUtils.getCooldownForPlayerInt("Report", (ProxiedPlayer) sender) + ChatColor.RED + " seconds!"));
            return;
        }
        else {
            StringBuilder b = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                b.append(args[i]).append(" ");
            }
            String reason = b.toString();

            sender.sendMessage(new TextComponent(ChatColor.GREEN + "You have successfully made a report!"));

            CooldownUtils.addCooldown("Report", (ProxiedPlayer) sender, 60);

            StaffUtils.getInstance().getReportManager().reportMessage((ProxiedPlayer) sender, reported, reason);
        }
    }
}
