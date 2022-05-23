package com.tazz.staffutilsbungee.listeners;

import com.tazz.staffutilsbungee.utils.Prefix;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if(!p.hasPermission("seabot.basic.staff")) return;
        Utils.sendMessageToStaff(Utils.c(Prefix.staff() + "&9" + p.getName() + "&a joined &bthe network. &7(Lobby-1)." ));
    }
}
