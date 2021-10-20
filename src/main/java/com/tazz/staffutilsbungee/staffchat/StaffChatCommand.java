package com.tazz.staffutilsbungee.staffchat;

import com.tazz.staffutilsbungee.StaffUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

public class StaffChatCommand extends Command {

    public static List<ProxiedPlayer> staffChat = new ArrayList<ProxiedPlayer>();

    public StaffChatCommand() {
        super("staffchat", "seabot.basic.staff", "sc");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to execute this command."));
        }
        if (!sender.hasPermission("seabot.basic.staff")) {
            sender.sendMessage(ChatColor.RED + "You must be helper or higher to use this command.");
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
            if (staffChat.contains(p)) {
                staffChat.remove(p);
                sender.sendMessage(new TextComponent(ChatColor.GREEN + "You have toggled staff chat off."));
                return;
            }
            if (!staffChat.contains(p)) {
                staffChat.add(p);
                sender.sendMessage(new TextComponent(ChatColor.GREEN + "You have toggled staff chat on."));
            }
        }
        else {
            StringBuilder b = new StringBuilder();
            for (String arg : args) {
                b.append(" ");
                b.append(arg);
            }
            String message = b.toString();
            if (sender.hasPermission("seabot.basic.staff")) {
                StaffUtils.getInstance().getStaffChatManager().staffChatMessage((ProxiedPlayer) sender, message);
            }
        }
    }
}
