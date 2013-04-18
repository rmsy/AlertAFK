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

import com.github.rmsy.alertafk.base.SimpleConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AAListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerConnect(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        AlertAFK.plugin.aaPlayers.put(e.getPlayer().getName(), new AAPlayer(player));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        SimpleConfigManager.savePlayerAliases(AlertAFK.plugin, aaPlayer);
        aaPlayer.aliases.clear();
        aaPlayer.aliases = null;
        AlertAFK.plugin.aaPlayers.remove(e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        if (e.isAsynchronous()) {
            aaPlayer.setNotAfk();
            Player[] onlinePlayers = Bukkit.getOnlinePlayers();
            for (Player onlinePlayer : onlinePlayers) {
                aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(onlinePlayer.getName());
                if (e.getMessage().contains(onlinePlayer.getName())) {
                    if (aaPlayer.afk) {
                        player.sendMessage(ChatColor.RED + "Note: " + onlinePlayer.getDisplayName() + ChatColor.RED + " is AFK - " + aaPlayer.afkMessage);
                    }
                } else {
                    if (aaPlayer.aliases.toArray().length > 0) {
                        for (Object alias : aaPlayer.aliases.toArray()) {
                            if (e.getMessage().contains(alias.toString())) {
                                if (aaPlayer.afk) {
                                    player.sendMessage(ChatColor.RED + "Note: " + onlinePlayer.getDisplayName() + ChatColor.RED + " is AFK - " + aaPlayer.afkMessage);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockDamage(BlockDamageEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemEnchant(EnchantItemEvent e) {
        Player player = e.getEnchanter();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onHangingEntityPlace(HangingPlaceEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryDismiss(InventoryCloseEvent e) {
        Player player = Bukkit.getPlayer(e.getPlayer().getName());
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryOpen(InventoryOpenEvent e) {
        Player player = Bukkit.getPlayer(e.getPlayer().getName());
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBedDepart(PlayerBedLeaveEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBucketFill(PlayerBucketFillEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerAutocomplete(PlayerChatTabCompleteEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEggThrow(PlayerEggThrowEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSlotChange(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPortalEntrance(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityShear(PlayerShearEntityEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFlightToggle(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSneakToggle(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSprintToggle(PlayerToggleSprintEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSignChange(SignChangeEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.plugin.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }
}