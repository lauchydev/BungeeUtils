package com.tazz.staffutilsbungee.stafftp;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.TeleportUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StaffTpCommand extends Command {

    public static List<ProxiedPlayer> staffChat = new ArrayList<ProxiedPlayer>();

    public StaffTpCommand() {
        super("stp", "seabot.basic.helper");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to execute this command."));
        }
        if (!sender.hasPermission("seabot.basic.staff")) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "No permission."));
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Missing Argumenets! Usage: /stp [ign]"));
            return;
        }
        if (args.length == 1) {
            ProxiedPlayer from = (ProxiedPlayer)sender;
            ProxiedPlayer to = ProxyServer.getInstance().getPlayer(args[0]);
            if (args[0] != null && to == null) {
                from.sendMessage(new TextComponent(ChatColor.RED + "This player is not online!"));
                return;
            }

            if(from.getServer().getInfo() == to.getServer().getInfo()){
                from.sendMessage(new TextComponent(ChatColor.RED + "You are already on " +  ChatColor.YELLOW + to.getName() + "'s Server."));
                return;
            }

            teleport(from, to);
            from.sendMessage(new TextComponent(ChatColor.GREEN + "Teleporting to " +  ChatColor.YELLOW + to.getName() + "'s Server."));
            return;
        }
        if (args.length == 2) {
            ProxiedPlayer from = ProxyServer.getInstance().getPlayer(args[0]);
            ProxiedPlayer to = ProxyServer.getInstance().getPlayer(args[1]);
            if (from == null) {
                sender.sendMessage(new TextComponent(ChatColor.RED + args[0] + " is not online!"));
                return;
            }
            if (to == null) {
                sender.sendMessage(new TextComponent(ChatColor.RED + args[1] + " is not online!"));
                return;
            }
            teleport(from, to);
            sender.sendMessage(new TextComponent(ChatColor.YELLOW + from.getName() + ChatColor.GREEN + " has been teleported to " + ChatColor.YELLOW + to.getName() + ChatColor.GREEN + "."));
        }
    }

    public static void teleport(ProxiedPlayer from, ProxiedPlayer to) {
        if (from.getServer().getInfo() != to.getServer().getInfo())
            from.connect(to.getServer().getInfo());

        ProxyServer.getInstance().getScheduler().schedule(StaffUtils.getInstance(), new Runnable() {
            @Override
            public void run() {
                TeleportUtil.teleport(from, to);
            }
        }, 1L, TimeUnit.SECONDS);
    }
}
