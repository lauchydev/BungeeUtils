package com.tazz.staffutilsbungee.listeners;

import com.tazz.staffutilsbungee.utils.ChatMessageUtils;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.packet.Chat;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();

            if (p.hasPermission("seabot.basic.staff")) {
                for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
                    if (online.hasPermission("seabot.basic.staff")) {
                        online.sendMessage(new TextComponent(ChatMessageUtils.getStaffPrefix() + ChatColor.BLUE + p.getName() + ChatColor.GREEN + " joined " + ChatColor.AQUA + "the network (Lobby-1)"));

                    }
                }
            }
    }
    @EventHandler
    public void onServerJoin(ServerSwitchEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String server = p.getServer().getInfo().getName();
        String from = e.getFrom().getName();


        // Checking if player switching server is staff
        if (p.hasPermission("seabot.basic.staff")) {
            for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
                // Checking for all online staff
                if (online.hasPermission("seabot.basic.staff")) {
                    // If joined server is the same as another staff member
                    if (server.equals(online.getServer().getInfo().getName())) { if (online.getServer().getInfo().getName().equals(server)) {
                            // Dont send the join message to the joining player
                            if (p == online) { return; }
                            online.sendMessage(new TextComponent(ChatMessageUtils.getStaffPrefix() + ChatColor.BLUE + p.getName() + ChatColor.AQUA + " joined your server (from " + from + ")"));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onServerLeave(ServerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String from = e.getTarget().getName();
        String server = e.getPlayer().getServer().getInfo().getName();
        if (p.hasPermission("seabot.basic.staff")) {
            for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
                // Checking for all online staff
                if (online.hasPermission("seabot.basic.staff")) {
                    // If joined server is the same as another staff member
                        if (online.getServer().getInfo().getName().equals(from)) {
                            // If they left the network, dont send a left to server message
                            if (from.equals(server)) return;
                            // What server the player switched to
                            online.sendMessage(new TextComponent(ChatMessageUtils.getStaffPrefix() + ChatColor.BLUE + p.getName() + ChatColor.AQUA + " left your server (to " + server + ")"));
                        }

                }
            }
        }
    }
}
