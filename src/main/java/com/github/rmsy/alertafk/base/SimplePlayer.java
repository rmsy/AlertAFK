/*
 * Copyright (C) 2013 Isaac Moore <rmsy@me.com>
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.rmsy.alertafk.base;

import com.github.rmsy.alertafk.AlertAFK;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public final class SimplePlayer implements com.github.rmsy.alertafk.Player {

    public Player player;
    public boolean afk;
    public String afkMessage;
    public List aliases;

    public SimplePlayer(Player p) {
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

    /**
     * Gets whether or not the player is AFK.
     *
     * @return Whether or not the player is AFK.
     */
    @Override
    public boolean isAfk() {
        return afk;
    }

    /**
     * Gets the relative Bukkit player.
     *
     * @return The relative Bukkit player.
     */
    @Nonnull
    @Override
    public Player getPlayer() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the player's current AFK message.
     *
     * @return The player's AFK message, or null if not AFK.
     */
    @Nullable
    @Override
    public String getAfkMessage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Sets the player as AFK with the specified message.
     *
     * @param message The message to be used when setting the player as AFK.
     */
    @Override
    public void setAfkWithMessage(@Nonnull String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Modifies the player's AFK message while they're AFK. If the player is not AFK, this is ignored.
     *
     * @param message The message to set.
     */
    @Override
    public void changeAfkMessage(@Nonnull String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Sets the player either to or from AFK.
     *
     * @param afk Whether or not the player is AFK.
     */
    public void setAfk(final boolean afk) {
        if (!(this.afk == afk)) {
            if (afk) {
                setAfk();
            } else {
                setNotAfk();
            }
        }
    }

    /**
     * Sets the player to AFK.
     */
    public void setAfk() {
        if (!this.afk) {
            this.afkMessage = AlertAFK.plugin.getDefaultAfkMessage();
            player.sendMessage(ChatColor.YELLOW + "You are now AFK: " + this.afkMessage);
            AlertAFK.plugin.broadcastAfk(this);
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
