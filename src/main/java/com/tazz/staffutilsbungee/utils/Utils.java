package com.tazz.staffutilsbungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Utils {

    public static TextComponent c(String msg){
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void sendMessageToStaff(TextComponent textComponent) {
        for (ProxiedPlayer online : ProxyServer.getInstance().getPlayers()) {
            if (online.hasPermission("seabot.basic.staff")) online.sendMessage(textComponent);
        }
    }
}
