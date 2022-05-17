package com.tazz.staffutilsbungee.messages;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import litebans.api.Database;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class ReplyCommand extends Command {
    public ReplyCommandManager replyCommandManager;
    public ReplyCommand(StaffUtils utils) {
        super("reply", "", "r");
        this.replyCommandManager = utils.replyCommandManager;
    }
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            UUID uuid = ((ProxiedPlayer) sender).getUniqueId();
            String ip = ((ProxiedPlayer) sender).getAddress().toString();
            boolean muted = Database.get().isPlayerMuted(uuid, ip);

            if (muted) {
                sender.sendMessage(Utils.c("&cCannot send a message while muted!"));
                return;
            }


            if(args.length == 0){
                sender.sendMessage(Utils.c("&cUsage: /reply (message)"));
                return;
            }

            ProxiedPlayer messageSender = (ProxiedPlayer) sender;

            UUID lastMessagedPlayer = replyCommandManager.getLastMessage(messageSender);
            if(lastMessagedPlayer == null){
                messageSender.sendMessage(Utils.c("&cNo one to reply to!"));
                return;
            }

            ProxiedPlayer receiver = StaffUtils.getInstance().getProxy().getPlayer(lastMessagedPlayer);
            if (receiver == null) {
                messageSender.sendMessage(Utils.c("That player is no longer online!"));
                replyCommandManager.removeLastMessage(messageSender);
                return;
            }

            StringBuilder msg = new StringBuilder();
            for (String arg : args) msg.append(arg).append(" ");

            replyCommandManager.message(receiver, messageSender, msg.toString());
        } else {
            // Command only for players
            sender.sendMessage(Utils.c("&cThis command is only for players."));
        }
    }
}
