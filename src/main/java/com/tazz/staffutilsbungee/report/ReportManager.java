package com.tazz.staffutilsbungee.report;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Prefix;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ReportManager {

    public ReportManager() {}

    public void reportMessage(ProxiedPlayer reporter, ProxiedPlayer reported, String reason) {
        Utils.sendMessageToStaff(Utils.c("&7[" + ServerUtils.getServer(reported) + "] " + Prefix.report() + "&e" + reporter.getName() + "&f has reported&e " + reported.getName() + ":"));
        Utils.sendMessageToStaff(Utils.c("  &fReason: &e" + reason));
    }
}
