package com.tazz.staffutilsbungee.report;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.ChatMessageUtils;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ReportManager {

    private final StaffUtils plugin;

    public ReportManager(StaffUtils plugin) {
        this.plugin = plugin;
    }

    public void reportMessage(ProxiedPlayer reporter, ProxiedPlayer reported, String reason) {
        for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
            if (online.hasPermission("seabot.basic.staff")) {
                online.sendMessage(new TextComponent(ChatColor.GRAY + "[" + ServerUtils.getServer(reported) + "] " + ChatMessageUtils.getReportPrefix() + "" + ChatColor.YELLOW + reporter.getName() + ChatColor.WHITE + " has reported " + ChatColor.YELLOW + reported.getName()
                        + ChatColor.WHITE + " for: " + ChatColor.YELLOW + reason));
            }
        }
    }
}
