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

import com.sun.xml.internal.ws.client.SenderException;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AlertAFK extends JavaPlugin {

    public static AlertAFK plugin;
    public HashMap aaPlayers = new HashMap();
    public List afkPlayers = new ArrayList();
    public List nonAfkPlayers = new ArrayList();
    public boolean broadcastGlobally;
    public String defaultAfkMessage;
    public FileConfiguration config;

    private static void setUpPlayers(HashMap hm, Player[] p) {
        for (Player aP : p) {
            hm.put(aP.getName(), new AAPlayer(aP));
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        AAConfig.setupConfig(plugin);
        getServer().getPluginManager().registerEvents(new AAListener(), plugin);
        Player[] players = Bukkit.getOnlinePlayers();
        if (players.length != 0) {
            setUpPlayers(aaPlayers, players);
        }
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
            Metrics.Graph afkStatus = metrics.createGraph("Global player AFK status");
            afkStatus.addPlotter(new Metrics.Plotter("AFK") {
                @Override
                public int getValue() {
                    return afkPlayers.size();
                }
            });
            afkStatus.addPlotter(new Metrics.Plotter("Active") {
                @Override
                public int getValue() {
                    return nonAfkPlayers.size();
                }
            });
        } catch (IOException e) {
            getLogger().warning("Unable to submit usage statistics:");
            getLogger().warning(e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        aaPlayers.clear();
        afkPlayers.clear();
        nonAfkPlayers.clear();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            AAPlayer aaPlayer = (AAPlayer) aaPlayers.get(sender.getName());
            if (alias.equalsIgnoreCase("afk") || alias.equalsIgnoreCase("a") || alias.equalsIgnoreCase("away")) {
                if (aaPlayer.afk) {
                    aaPlayer.setNotAfk();
                } else if (args.length > 0) {
                    aaPlayer.setAfk(StringUtils.join(args, " "));
                } else {
                    aaPlayer.setAfk();
                }
            } else if (alias.equalsIgnoreCase("alias")) {
                if ((args.length >= 1) && args[0].equalsIgnoreCase("clear")) {
                    if (aaPlayer.aliases != null) {
                        aaPlayer.aliases.clear();
                        aaPlayer.aliases = null;
                    }
                } else if ((args.length >= 1) && args[0].equalsIgnoreCase("list")) {
                    if (aaPlayer.aliases != null) {
                        sender.sendMessage(ChatColor.YELLOW + "Your aliases: " + StringUtils.join(aaPlayer.aliases.toArray(), ", "));
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "You don't have any aliases.");
                    }
                } else if ((args.length >= 2) && args[0].equalsIgnoreCase("add")) {
                    if (args[1].length() >= 3) {
                        if (aaPlayer.aliases != null) {
                            aaPlayer.aliases.add(args[1]);
                            sender.sendMessage(ChatColor.YELLOW + args[2] + ChatColor.YELLOW + " has been added to your aliases.");
                        } else {
                            aaPlayer.aliases = new ArrayList();
                            aaPlayer.aliases.add(args[1]);
                            sender.sendMessage(ChatColor.YELLOW + args[2] + ChatColor.YELLOW + " has been added to your aliases.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Aliases must be at least 3 characters in length.");
                    }
                } else if ((args.length >= 2) && args[0].equalsIgnoreCase("remove")) {
                    if (aaPlayer.aliases == null) {
                        sender.sendMessage(ChatColor.YELLOW + "You don't have any aliases.");
                    } else if (aaPlayer.aliases.contains(args[2])) {
                        aaPlayer.aliases.remove(args[2]);
                        sender.sendMessage(ChatColor.YELLOW + args[2] + ChatColor.YELLOW + " has been removed from your aliases.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /alias <add/remove/list/clear> [alias]");
                }
            }
            return true;
        }
        return false;
    }
}
