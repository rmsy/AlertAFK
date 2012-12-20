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
// import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

/**
 * @author Isaac Moore <rmsy@me.com>
 */
public class AAListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerConnect(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        AlertAFK.aaPlayers.put(e.getPlayer().getName(), new AAPlayer(player));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        AlertAFK.aaPlayers.remove(e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
        if (e.isAsynchronous()) {
            Player[] onlinePlayers = Bukkit.getOnlinePlayers();
            for (int i = 0; i < onlinePlayers.length; i++) {
                if ((e.getMessage().indexOf(onlinePlayers[i].getName())) != -1) {
                    aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(onlinePlayers[i].getName());
                    if (aaPlayer.afk) {
                        player.sendMessage(ChatColor.RED + "Note: " + onlinePlayers[i].getDisplayName() + ChatColor.RED + " is AFK - " + aaPlayer.afkMessage);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockDamage(BlockDamageEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemEnchant(EnchantItemEvent e) {
        Player player = e.getEnchanter();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onFurnaceItemExtract(FurnaceExtractEvent e) {
//        Player player = e.getPlayer();
//        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
//        aaPlayer.setNotAfk();
//    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onHangingEntityPlace(HangingPlaceEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryDismiss(InventoryCloseEvent e) {
        Player player = Bukkit.getPlayer(e.getPlayer().getName());
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryOpen(InventoryOpenEvent e) {
        Player player = Bukkit.getPlayer(e.getPlayer().getName());
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBedDepart(PlayerBedLeaveEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBucketFill(PlayerBucketFillEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerAutocomplete(PlayerChatTabCompleteEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEggThrow(PlayerEggThrowEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSlotChange(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPortalEntrance(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityShear(PlayerShearEntityEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFlightToggle(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSneakToggle(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSprintToggle(PlayerToggleSprintEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSignChange(SignChangeEvent e) {
        Player player = e.getPlayer();
        AAPlayer aaPlayer = (AAPlayer) AlertAFK.aaPlayers.get(player.getName());
        aaPlayer.setNotAfk();
    }
}
