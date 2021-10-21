package com.tazz.staffutilsbungee.helpop;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Prefix;
import com.tazz.staffutilsbungee.utils.ServerUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class HelpOpManager {

    public HelpOpManager() {}

    public void helpopMessage(ProxiedPlayer p, String reason) {
        Utils.sendMessageToStaff(Utils.c("&7[" + ServerUtils.getServer(p) + "] " + Prefix.helpop() + "&e" + p.getName() + "&f requested assistance:"));
        Utils.sendMessageToStaff(Utils.c("   &fReason: &e" + reason));
    }
}
