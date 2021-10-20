package com.tazz.staffutilsbungee;

import com.tazz.staffutilsbungee.helpop.HelpOpCommand;
import com.tazz.staffutilsbungee.helpop.HelpOpManager;
import com.tazz.staffutilsbungee.listeners.JoinListener;
import com.tazz.staffutilsbungee.listeners.LeaveListener;
import com.tazz.staffutilsbungee.messages.MsgCommand;
import com.tazz.staffutilsbungee.report.ReportCommand;
import com.tazz.staffutilsbungee.report.ReportManager;
import com.tazz.staffutilsbungee.staffchat.StaffChatCommand;
import com.tazz.staffutilsbungee.staffchat.StaffChatListener;
import com.tazz.staffutilsbungee.staffchat.StaffChatManager;
import com.tazz.staffutilsbungee.stafftp.StaffTpCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class StaffUtils extends Plugin {

    public static StaffUtils instance;
    public HelpOpManager helpOpManager;
    public ReportManager reportManager;
    public StaffChatManager staffChatManager;

    public static String channel = "DragSimStaff";

    public void onEnable() {

        instance = this;

        ProxyServer.getInstance().registerChannel(channel);

        getProxy().getPluginManager().registerListener(this, new JoinListener());
        getProxy().getPluginManager().registerListener(this, new LeaveListener());
        getProxy().getPluginManager().registerListener(this, new StaffChatListener());
        getProxy().getPluginManager().registerCommand(this, new MsgCommand());
        getProxy().getPluginManager().registerCommand(this, new HelpOpCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffTpCommand());
        getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());

        registerManagers();
    }

    private void registerManagers() {
        this.helpOpManager = new HelpOpManager(this);
        this.reportManager = new ReportManager(this);
        this.staffChatManager = new StaffChatManager(this);
    }

    public static String getChannel() {
        return channel;
    }

    public static StaffUtils getInstance() {
        return instance;
    }

    public HelpOpManager getHelpOpManager() {
        return helpOpManager;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }

    public StaffChatManager getStaffChatManager() {
        return staffChatManager;
    }

}
