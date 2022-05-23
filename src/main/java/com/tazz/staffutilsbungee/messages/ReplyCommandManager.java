package com.tazz.staffutilsbungee.messages;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class ReplyCommandManager {
    // Receiver, Sender
    private final HashMap<UUID, UUID> lastMessageCache = new HashMap<>();

    public ReplyCommandManager() {}

    public void setLastMessage(ProxiedPlayer receiver, ProxiedPlayer sender){
        lastMessageCache.put(receiver.getUniqueId(), sender.getUniqueId());
    }

    public void removeLastMessage(ProxiedPlayer player){
        lastMessageCache.remove(player.getUniqueId());
    }

    public UUID getLastMessage(ProxiedPlayer player){
        return lastMessageCache.getOrDefault(player.getUniqueId(), null);
    }

    public void message(ProxiedPlayer receiver, ProxiedPlayer sender, String msg){
        // Sending message TO player
        sender.sendMessage(Utils.c("&dTo &f" + receiver.getDisplayName() + "&7: " + msg));
        // Recieving message FROM player
        receiver.sendMessage(Utils.c("&dFrom &f" + sender.getDisplayName() + "&7: " + msg));

        setLastMessage(receiver, sender);
    }
}
