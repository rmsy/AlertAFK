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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
/**
 * All listeners used in AlertAFK are defined and implemented here.
 */
public class AAListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerConnect(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        AlertAFK.aaPlayers.put(e.getPlayer().getName(), new AAPlayer(player));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        AlertAFK.aaPlayers.remove(e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player);
        aaPlayer.setNotAfk();
        if (e.isAsynchronous()) {
            Player[] onlinePlayers = Bukkit.getOnlinePlayers();
            for (int i = 0; i < onlinePlayers.length; i++) {
                if ((e.getMessage().indexOf(onlinePlayers[i].getName())) != -1) {
                    aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(onlinePlayers[i]);
                    player.sendMessage(ChatColor.YELLOW + onlinePlayers[i].getDisplayName() + ChatColor.YELLOW + " is AFK - " + aaPlayer.afkMessage);
                }
            }
        }
    }
}
