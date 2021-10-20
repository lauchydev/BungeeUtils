package com.tazz.staffutilsbungee.listeners;

import com.tazz.staffutilsbungee.utils.ChatMessageUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String server = p.getServer().getInfo().getName();

        if(p.hasPermission("seabot.basic.admin"))
            return;

        if (p.hasPermission("seabot.basic.staff")) {
            for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
                if (online.hasPermission("seabot.basic.staff")) {
                    online.sendMessage(new TextComponent(ChatMessageUtils.getStaffPrefix() + ChatColor.BLUE + p.getName() + ChatColor.RED + " left " + ChatColor.AQUA + "the network (from " + server + ChatColor.AQUA + ")"));
                }
            }
        }
    }
}
