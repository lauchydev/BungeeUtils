package com.tazz.staffutilsbungee.listeners;

import com.tazz.staffutilsbungee.utils.Prefix;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerSwitchListener implements Listener {

    @EventHandler
    public void onServerJoin(ServerSwitchEvent e) {
        ProxiedPlayer p = e.getPlayer();
        ServerInfo to = p.getServer().getInfo();
        ServerInfo from = e.getFrom();

        // Checking if player switching server is staff
        if (!p.hasPermission("seabot.basic.staff")) return;

        for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
            if (online.hasPermission("seabot.basic.staff")) {
                // If joined server is the same as another staff member
                if(to.equals(online.getServer().getInfo())){
                    // Make sure not player themselves
                    if(p == online) continue;

                    online.sendMessage(Utils.c(Prefix.staff() + "&9" + p.getName() + " &bjoined your server. &7(from " + from.getName() + ")"));
                }
            }
        }
    }

    @EventHandler
    public void onServerLeave(ServerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        ServerInfo from = e.getTarget();
        ServerInfo to = e.getPlayer().getServer().getInfo();

        // Checking if player disconnecting is staff
        if (!p.hasPermission("seabot.basic.staff")) return;

        for (ProxiedPlayer loopPlayer : ProxyServer.getInstance().getPlayers()) {
            // Checking for all online staff
            if (loopPlayer.hasPermission("seabot.basic.staff")) {
                // If joined server is the same as another staff member
                if (loopPlayer.getServer().getInfo().equals(from)) {
                    // If they left the network, dont send a left to server message
                    if (from.equals(to)) return;

                    loopPlayer.sendMessage(Utils.c(Prefix.staff() + "&9" + p.getName() + " &bleft your server. &7(to " + to.getName() + ")"));
                }
            }
        }
    }
}
