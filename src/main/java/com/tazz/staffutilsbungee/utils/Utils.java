package com.tazz.staffutilsbungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class Utils {

    public static TextComponent c(String msg){
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
