package com.tazz.staffutilsbungee.utils;

import net.md_5.bungee.api.ChatColor;

public class ChatMessageUtils {
    public static String getStaffPrefix(){
        return ChatColor.DARK_AQUA + "[Staff] ";
    }

    public static String getReportPrefix(){
        return ChatColor.RED + "[Report] ";
    }

    public static String getHelpopPrefix(){
        return ChatColor.DARK_PURPLE + "[Request] ";
    }
}
