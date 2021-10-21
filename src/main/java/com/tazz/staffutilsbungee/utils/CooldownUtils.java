package com.tazz.staffutilsbungee.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class CooldownUtils {

    private static HashMap<String, HashMap<UUID, Long>> cooldown = new HashMap();

    public static void clearCooldowns() { cooldown.clear(); }

    public static HashMap<UUID, Long> getCdMap(String coolDownName){
        return cooldown.get(coolDownName);
    }

    public static boolean hasCdMap(String coolDownName){
        return cooldown.containsKey(coolDownName);
    }

    public static void createCooldown(String k) {
        if (hasCdMap(k)) {
            throw new IllegalArgumentException("This cooldown exists.");
        }
        cooldown.put(k, new HashMap<UUID, Long>());
    }

    public static void addCooldown(String k, ProxiedPlayer p, int seconds) {
        if (!hasCdMap(k)) {
            throw new IllegalArgumentException(k + " does not exist");
        }
        long next = System.currentTimeMillis() + seconds * 1000L;
        getCdMap(k).put(p.getUniqueId(), next);
    }

    public static boolean isOnCooldown(String k, ProxiedPlayer p) {
        if(!hasCdMap(k)) return false;
        HashMap<UUID, Long> cdMap = getCdMap(k);
        if(!cdMap.containsKey(p.getUniqueId())) return false;

        return System.currentTimeMillis() <= cdMap.get(p.getUniqueId());
    }

    public static int getCooldown(String coolDownName, ProxiedPlayer proxiedPlayer) { // Should always use meaningful names
        HashMap<UUID, Long> cdMap = cooldown.get(coolDownName);
        if(cdMap == null) return 0;

        long cdInSeconds = cdMap.getOrDefault(proxiedPlayer.getUniqueId(), 0L);
        cdInSeconds -= System.currentTimeMillis() / 1000L;

        return (int) cdInSeconds;
    }

    public static void removeCooldown(String k, ProxiedPlayer p) {
        cooldown.remove(k);
    }
}

