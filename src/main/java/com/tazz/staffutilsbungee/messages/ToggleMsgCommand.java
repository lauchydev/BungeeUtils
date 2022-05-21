package com.tazz.staffutilsbungee.messages;

import com.tazz.staffutilsbungee.StaffUtils;
import com.tazz.staffutilsbungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class ToggleMsgCommand extends Command {

    private MessageManager manager;

    public ToggleMsgCommand(StaffUtils utils) {
        super("togglemsg", "", "toggledm");
        this.manager = utils.messageManager;
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer messageSender = (ProxiedPlayer) sender;
            UUID uuid = messageSender.getUniqueId();
            if(manager.disabled.contains(uuid)) manager.disabled.remove(uuid);
            else manager.disabled.add(uuid);

            messageSender.sendMessage(Utils.c("&aYou can " + (manager.disabled.contains(uuid) ? "no longer" : "now")+ " receive private messages."));
        } else {
            // Command only for players
            sender.sendMessage(Utils.c("&cThis command is only for players."));
        }
    }
}
