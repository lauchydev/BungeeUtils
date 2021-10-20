package com.tazz.staffutilsbungee.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerUtils {
    public static String getServer(ProxiedPlayer p) {
        return p.getServer().getInfo().getName();
    }
}
