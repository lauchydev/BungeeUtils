package com.tazz.staffutilsbungee.utils;

import com.tazz.staffutilsbungee.StaffUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class TeleportUtil {
    public static void teleport(ProxiedPlayer from, ProxiedPlayer to) {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOut);
        try {
            out.writeUTF("Teleport");
            out.writeUTF(from.getName());
            out.writeUTF(to.getName());
            from.getServer().getInfo()
                    .sendData(StaffUtils.channel, byteArrayOut.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
