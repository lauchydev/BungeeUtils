package com.tazz.staffutilsbungee.messages;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MsgCommand extends Command {

    public MsgCommand() {
        super("msg", "", "tell", "say");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            // Using the Bungeecord API, players are known as ProxiedPlayer, since bungeecord is a proxy
            ProxiedPlayer messageSender = (ProxiedPlayer) sender;

            // If the message has atleast 2 arguments e.g /msg [ARG 1 - target name] [ARG 2 - message]
            if (args.length >= 2) {
                // Get the target receiver
                ProxiedPlayer messageReceiver = StaffUtils.getInstance().getProxy().getPlayer(args[0]);
                if (messageReceiver == null) {
                    // Target player offline, send a message back to message sender
                    messageSender.sendMessage(Utils.c("&cPlayer '" + args[0] + "'&c was not found"));
                    return;
                }

                // If player trying to message themselves
                if (messageSender.equals(messageReceiver)) {
                    messageSender.sendMessage(Utils.c("&cYou cannot message this player"));
                    return;
                }
                StringBuilder msg = new StringBuilder();
                for (int i = 1; i < args.length; i++)
                    msg.append(args[i]).append(" ");
                // Sending message TO player
                messageSender.sendMessage(Utils.c("&dTo &7" + messageReceiver.getDisplayName() + "&7: " + msg));
                // Recieving message FROM player
                messageReceiver.sendMessage(Utils.c("&dFrom &7" + messageSender.getDisplayName() + "&7: " + msg));

                // TODO: Add /r
            } else {
                // Incorrect usage
                messageSender.sendMessage(Utils.c("&cUsage: /msg (player) (message)"));
            }
        } else {
            // Command only for players
            sender.sendMessage(Utils.c("&cThis command is only for players."));
        }
    }
}
