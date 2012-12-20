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

//  Bukkit imports
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

//  Java imports
import java.util.HashMap;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AlertAFK extends JavaPlugin {

    public static HashMap aaPlayers = new HashMap();

    @Override
    public void onEnable() {
        Player[] players = Bukkit.getOnlinePlayers();
        if (players.length != 0) {
            populateHashMap(aaPlayers, players);
        }
    }

    @Override
    public void onDisable() {
        aaPlayers.clear();
    }

    private static void populateHashMap(HashMap hm, Player[] p) {
        for (int i = 0; i < p.length; i++) {
            hm.put(p[i].getName(), new AAPlayer(p[i]));
        }
    }
}
