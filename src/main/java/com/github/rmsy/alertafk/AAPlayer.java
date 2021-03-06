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

import java.util.List;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AAPlayer {

    public Player player;
    public boolean afk;
    public String afkMessage;
    public List aliases;

    public AAPlayer(Player p) {
        this.player = p;
        this.afk = false;
        this.afkMessage = AlertAFK.plugin.defaultAfkMessage;
        this.aliases = AlertAFK.plugin.config.getStringList("aliases." + this.player.getName());
        AlertAFK.plugin.nonAfkPlayers.add(this.player);
    }

    public void setNotAfk() {
        if (this.afk) {
            this.player.sendMessage(ChatColor.YELLOW + "You are no longer AFK.");
            if (AlertAFK.plugin.broadcastGlobally) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Note: " + this.player.getDisplayName() + ChatColor.YELLOW + " is no longer AFK.");
            }
            this.afk = false;
            this.afkMessage = null;
            AlertAFK.plugin.afkPlayers.remove(this.player);
            AlertAFK.plugin.nonAfkPlayers.add(this.player);
        }
    }

    public void setAfk() {
        if (!this.afk) {
            this.afkMessage = AlertAFK.plugin.defaultAfkMessage;
            player.sendMessage(ChatColor.YELLOW + "You are now AFK - " + this.afkMessage);
            if (AlertAFK.plugin.broadcastGlobally) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Note: " + this.player.getDisplayName() + ChatColor.YELLOW + " is now AFK - " + this.afkMessage);
            }
            this.afk = true;
            AlertAFK.plugin.nonAfkPlayers.remove(this.player);
            AlertAFK.plugin.afkPlayers.add(this.player);
        }
    }

    public void setAfk(String msg) {
        if (!this.afk) {
            this.afkMessage = msg;
            player.sendMessage(ChatColor.YELLOW + "You are now AFK - " + this.afkMessage);
            if (AlertAFK.plugin.broadcastGlobally) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Note: " + this.player.getDisplayName() + ChatColor.YELLOW + " is now AFK - " + this.afkMessage);
            }
            this.afk = true;
            AlertAFK.plugin.nonAfkPlayers.remove(this.player);
            AlertAFK.plugin.afkPlayers.add(this.player);
        }
    }
}
