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

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
/**
 * Represents a player on the server, who can be either active or AFK
 */
public class AAPlayer {

    /**
     * Bukkit player
     */
    public Player player;
    /**
     * If the player is AFK
     */
    public boolean afk;
    /**
     * The player's custom AFK message
     */
    public String afkMessage;
    /**
     * The default AFK message, defined in config.yml
     */
    public static String defaultAfkMessage;

    /**
     * Creates a new AAPlayer and initializes it with default values.
     *
     * @param p The player.
     */
    public AAPlayer(Player p) {
        this.player = p;
        this.afk = false;
        this.afkMessage = AAPlayer.defaultAfkMessage;
    }

    public void setNotAfk() {
        if (this.afk) {
            this.player.sendMessage(ChatColor.YELLOW + "You are no longer AFK.");
            this.afk = false;
        }
    }

    public void setAfk() {
        if (!this.afk) {
            player.sendMessage(ChatColor.YELLOW + "You are now AFK - " + ChatColor.YELLOW + AAPlayer.defaultAfkMessage);
            this.afkMessage = AAPlayer.defaultAfkMessage;
            this.afk = true;
        }
    }

    public void setAfk(String msg) {
        if (!this.afk) {
            this.afkMessage = msg;
            player.sendMessage(ChatColor.YELLOW + "You are now AFK - " + ChatColor.YELLOW + this.afkMessage);
            this.afk = true;
        }
    }
}
