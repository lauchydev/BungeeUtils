package com.tazz.staffutilsbungee.staffchat;

import com.tazz.staffutilsbungee.utils.ServerUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class StaffChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer)) {
            return;
        }
        if (e.getMessage().startsWith("/")) {
            return;
        }
        ProxiedPlayer sender = (ProxiedPlayer)e.getSender();

        if (!sender.hasPermission("staffutils.staffchat")) {
            return;
        }
        if (!StaffChatCommand.staffChat.contains(sender)) {
            return;
        }
        else {
            e.setCancelled(true);
            for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
                if (online.hasPermission("staffutils.staffchat")) {
                    online.sendMessage(ChatColor.GRAY + "[" + ServerUtils.getServer(sender) + "] " + ChatColor.BLUE + sender.getName() + ChatColor.GRAY + ": " + ChatColor.AQUA + e.getMessage());
                }
            }
        }

    }
}
