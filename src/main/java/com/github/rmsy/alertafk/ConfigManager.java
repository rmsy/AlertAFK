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

/**
 * Interface for managing the plugin's configuration.
 */
public interface ConfigManager {
    /**
     * Sets up the configuration file.
     */
    void setupConfig();

    /**
     * Saves a player's aliases to the configuration.
     *
     * @param player The player whose aliases should be saved.
     */
    void savePlayerAliases(Player player);

    /**
     * Saves every online player's aliases to the configuration.
     */
    void saveAllAliases();
}
