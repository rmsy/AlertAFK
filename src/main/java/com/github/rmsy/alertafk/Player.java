/*
 * Copyright (C) 2013 Isaac Moore <rmsy@me.com>
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.rmsy.alertafk;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a player on the server.
 */
public interface Player {
    /**
     * Gets the player's current AFK message.
     *
     * @return The player's AFK message, or null if not AFK.
     */
    @Nullable
    String getAfkMessage();

    /**
     * Sets the player as AFK with the specified message.
     *
     * @param message The message to be used when setting the player as AFK.
     */
    void setAfkWithMessage(@Nonnull final String message);

    /**
     * Modifies the player's AFK message while they're AFK. If the player is not AFK, this is ignored.
     *
     * @param message The message to set.
     */
    void changeAfkMessage(@Nonnull final String message);

    /**
     * Sets the player either to or from AFK.
     *
     * @param afk Whether or not the player is AFK.
     */
    void setAfk(final boolean afk);

    /**
     * Sets the player to AFK.
     */
    void setAfk();

    /**
     * Sets the player as not AFK.
     */
    void setNotAfk();

    /**
     * Gets whether or not the player is AFK.
     *
     * @return Whether or not the player is AFK.
     */
    boolean isAfk();

    /**
     * Gets the relative Bukkit player.
     *
     * @return The relative Bukkit player.
     */
    @Nonnull
    org.bukkit.entity.Player getPlayer();
}
