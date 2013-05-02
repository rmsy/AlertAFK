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
import com.github.rmsy.alertafk.ConfigManager;
import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public final class SimpleConfigManager implements ConfigManager {

    /**
     * The plugin instance.
     */
    @Nonnull
    private final AlertAFK plugin;
    /**
     * The configuration file.
     */
    @Nonnull
    private FileConfiguration config;

    /**
     * Creates a new config manager.
     *
     * @param plugin The pluign instance.
     */
    public SimpleConfigManager(@Nonnull final AlertAFK plugin) {
        this.plugin = Preconditions.checkNotNull(plugin, "SimpleConfigManager constructor got null plugin.");
    }

    private SimpleConfigManager() {
        this.plugin = null;
    }

    public void setupConfig() {
        plugin.saveDefaultConfig();
        plugin.saveConfig();
        this.config = plugin.getConfig();
        boolean broadcastGlobally = config.getBoolean("aesthetic.broadcastGlobally", true);
        String outdatedAfkMessage = config.getString("aesthetic.defaultAfkMessage");
        List<String> afkMessages = config.getStringList("aesthetic.defaultAfkMessages");
        if (outdatedAfkMessage != null) {
            Preconditions.checkNotNull(afkMessages, "setupConfig() found an empty AFK message list; why aren't the defaults loaded?").add(outdatedAfkMessage);
            config.set("aesthetic.defaultAfkMessage", null);
        }

    }

    /**
     * Saves a player's aliases to the configuration.
     *
     * @param player The player whose aliases should be saved.
     */
    @Override
    public void savePlayerAliases(com.github.rmsy.alertafk.Player player) {
        FileConfiguration config = plugin.getConfig();
        if (config != plugin.config) {
            setupConfig();
        }
        if (player.aliases.toArray().length > 0) {
            plugin.config.set("aliases." + player.player.getName(), player.aliases);
        }
        plugin.saveConfig();
    }

    /**
     * Saves every online player's aliases to the configuration.
     */
    @Override
    public void saveAllAliases() {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player p : onlinePlayers) {
            SimplePlayer simplePlayer = (SimplePlayer) plugin.aaPlayers.get(p.getName());
            savePlayerAliases(simplePlayer);
        }
        plugin.saveConfig();
    }

}
