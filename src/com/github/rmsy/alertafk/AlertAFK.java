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

import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.mcstats.Metrics;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AlertAFK extends JavaPlugin {

    public static HashMap aaPlayers = new HashMap();
    public static List afkPlayers = new ArrayList();
    public static List nonAfkPlayers = new ArrayList();
    private static AlertAFK plugin = null;
    public static boolean broadcastGlobally;
    public static String defaultAfkMessage;

    @Override
    public void onEnable() {
        plugin = this;
        Player[] players = Bukkit.getOnlinePlayers();
        if (players.length != 0) {
            populateHashMap(aaPlayers, players);
        }
//        try {
//            Metrics metrics = new Metrics(this);
//            metrics.start();
//            Metrics.Graph afkStatus = metrics.createGraph("Global player AFK status");
//            afkStatus.addPlotter(new Metrics.Plotter("AFK") {
//                @Override
//                public int getValue() {
//                    return afkPlayers.size();
//                }
//            });
//            afkStatus.addPlotter(new Metrics.Plotter("Active") {
//                @Override
//                public int getValue() {
//                    return nonAfkPlayers.size();
//                }
//            });
//        } catch (IOException e) {
//            getLogger().warning("Unable to submit usage statistics:");
//            getLogger().warning(e.getMessage());
//        }
    }

    @Override
    public void onDisable() {
        aaPlayers.clear();
        afkPlayers.clear();
        nonAfkPlayers.clear();
    }

    private static void populateHashMap(HashMap hm, Player[] p) {
        for (int i = 0; i < p.length; i++) {
            hm.put(p[i].getName(), new AAPlayer(p[i]));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (alias.equalsIgnoreCase("afk") || alias.equalsIgnoreCase("a") || alias.equalsIgnoreCase("away")) {
            AAPlayer aaPlayer = (AAPlayer) aaPlayers.get(sender.getName());
            if (aaPlayer.afk) {
                aaPlayer.setNotAfk();
            } else if (args.length > 0) {
                aaPlayer.setAfk(StringUtils.join(args, " "));
            } else {
                aaPlayer.setAfk();
            }
            return true;
        }
        return false;
    }
}
