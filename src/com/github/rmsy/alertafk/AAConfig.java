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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AAConfig {

    public static void setupConfig(AlertAFK plugin) {
        plugin.saveDefaultConfig();
        plugin.saveConfig();
        plugin.config = plugin.getConfig();
        plugin.broadcastGlobally = plugin.config.getBoolean("aesthetic.broadcastGlobally");
        plugin.defaultAfkMessage = plugin.config.getString("aesthetic.defaultAfkMessage");
    }

    public static void savePlayerAliases(AlertAFK plugin, AAPlayer player) {
        FileConfiguration config = plugin.getConfig();
        if (config != plugin.config) {
            setupConfig(plugin);
        }
        if (player.aliases.toArray().length > 0) {
            plugin.config.set("aliases." + player.player.getName(), player.aliases);
        }
        plugin.saveConfig();
    }

    public static void saveAllAliases(AlertAFK plugin) {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player p : onlinePlayers) {
            AAPlayer aaPlayer = (AAPlayer) plugin.aaPlayers.get(p.getName());
            savePlayerAliases(plugin, aaPlayer);
        }
        plugin.saveConfig();
    }
}
