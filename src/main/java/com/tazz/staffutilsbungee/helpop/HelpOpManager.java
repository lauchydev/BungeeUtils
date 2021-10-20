package com.tazz.staffutilsbungee.helpop;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.ChatMessageUtils;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class HelpOpManager {

    private final StaffUtils plugin;

    public HelpOpManager(StaffUtils plugin) {
        this.plugin = plugin;
    }

    public void helpopMessage(ProxiedPlayer p, String reason) {
        for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
            if (online.hasPermission("seabot.basic.mod")) {
                online.sendMessage(new TextComponent(ChatColor.GRAY + "[" + ServerUtils.getServer(p) + "] " + ChatMessageUtils.getHelpopPrefix() + ChatColor.YELLOW + p.getName() +  ChatColor.WHITE + "  requested assistance"));
                online.sendMessage(new TextComponent(ChatColor.WHITE + "Reason: " + ChatColor.YELLOW + reason));
            }
        }
    }
}
