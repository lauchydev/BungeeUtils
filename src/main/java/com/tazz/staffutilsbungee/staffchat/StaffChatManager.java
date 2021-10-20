package com.tazz.staffutilsbungee.staffchat;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.ChatMessageUtils;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StaffChatManager {

    private final StaffUtils plugin;

    public StaffChatManager(StaffUtils plugin) {
        this.plugin = plugin;
    }

    public void staffChatMessage(ProxiedPlayer p, String message) {
        for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
            if (online.hasPermission("seabot.basic.staff")) {
                online.sendMessage(new TextComponent(ChatColor.GRAY + "[" + ServerUtils.getServer(p) + "] " + ChatMessageUtils.getStaffPrefix() + "" + ChatColor.AQUA + p.getName() + ChatColor.GRAY + ":" + ChatColor.BLUE + message));
            }
        }
    }
}
