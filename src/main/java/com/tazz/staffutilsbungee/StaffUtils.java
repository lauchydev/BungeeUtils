package com.tazz.staffutilsbungee;

import com.tazz.staffutilsbungee.helpop.HelpOpCommand;
import com.tazz.staffutilsbungee.helpop.HelpOpManager;
import com.tazz.staffutilsbungee.listeners.JoinListener;
import com.tazz.staffutilsbungee.listeners.LeaveListener;
import com.tazz.staffutilsbungee.listeners.ServerSwitchListener;
import com.tazz.staffutilsbungee.messages.MsgCommand;
import com.tazz.staffutilsbungee.messages.ReplyCommand;
import com.tazz.staffutilsbungee.messages.ReplyCommandManager;
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
    public ReplyCommandManager replyCommandManager;
    public StaffChatManager staffChatManager;

    public static String channel = "DragSimStaff";

    public void onEnable() {
        instance = this;

        ProxyServer.getInstance().registerChannel(channel);

        // Listeners
        getProxy().getPluginManager().registerListener(this, new JoinListener());
        getProxy().getPluginManager().registerListener(this, new LeaveListener());
        getProxy().getPluginManager().registerListener(this, new ServerSwitchListener());
        getProxy().getPluginManager().registerListener(this, new StaffChatListener());

        // Managers
        registerManagers();

        // Commands
        getProxy().getPluginManager().registerCommand(this, new MsgCommand(this));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand(this));
        getProxy().getPluginManager().registerCommand(this, new HelpOpCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffTpCommand());
        getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
    }

    private void registerManagers() {
        this.helpOpManager = new HelpOpManager();
        this.reportManager = new ReportManager();
        this.staffChatManager = new StaffChatManager();
        this.replyCommandManager = new ReplyCommandManager();
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
