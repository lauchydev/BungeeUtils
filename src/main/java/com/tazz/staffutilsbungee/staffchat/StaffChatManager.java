package com.tazz.staffutilsbungee.staffchat;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Prefix;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StaffChatManager {

    public StaffChatManager() { }

    public void staffChatMessage(ProxiedPlayer p, String message) {
        Utils.sendMessageToStaff(Utils.c("&7[ " + ServerUtils.getServer(p) + "] " + Prefix.staff() + "&b" + p.getName() + "&7: &9" + message));
    }
}
