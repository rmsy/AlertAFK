/*
 * Copyright (C) 2012 Isaac Moore <rmsy@me.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.rmsy.alertafk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AAListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerConnect(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        AlertAFK.plugin.aaPlayers.put(e.getPlayer().getName(), new AAPlayer(player));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        AAConfig.savePlayerAliases(AlertAFK.plugin, aaPlayer);
        AlertAFK.plugin.aaPlayers.remove(e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
        if (e.isAsynchronous()) {
            Player[] onlinePlayers = Bukkit.getOnlinePlayers();
            for (int i = 0; i < onlinePlayers.length; i++) {
                if ((e.getMessage().indexOf(onlinePlayers[i].getName())) != -1) {
                    aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(onlinePlayers[i].getName());
                    if (aaPlayer.afk) {
                        player.sendMessage(ChatColor.RED + "Note: " + onlinePlayers[i].getDisplayName() + ChatColor.RED + " is AFK - " + aaPlayer.afkMessage);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerEvent(PlayerEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerAnimation(PlayerAnimationEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChannelChange(PlayerChannelEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerFish(PlayerFishEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerKick(PlayerKickEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerLevelChange(PlayerLevelChangeEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerPickupItem(PlayerPickupItemEvent e) {
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerVelocityChange(PlayerVelocityEvent e) {
    }
}
