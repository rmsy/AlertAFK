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

import com.google.common.base.Preconditions;
import com.sk89q.minecraft.util.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

/**
 * User commands.
 */
public final class Commands {
    /**
     * Command to toggle AFK mode.
     *
     * @param context The context in which the command was executed.
     * @param sender  The executor of the command.
     * @throws CommandException
     */
    @Command(
            aliases = {"afk", "away"},
            desc = "Toggles AFK mode",
            anyFlags = false,
            flags = "p:",
            help = "This command enables AFK mode (if it is disabled) or disables AFK mode (if it is enabled).",
            min = 0,
            usage = "[-p:<player>] [message]"
    )
    public void afkCommand(@Nonnull final CommandContext context, @Nonnull final CommandSender sender) throws CommandException {
        if (Preconditions.checkNotNull(sender, "afkCommand() got null CommandSender") instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player target = (org.bukkit.entity.Player) sender;
            Player aaTarget = Preconditions.checkNotNull(AlertAFK.plugin.getPlayer(target.getName()), "afkCommand() found null AAPlayer: %s", target.getDisplayName());
            if (aaTarget.isAfk()) {
                aaTarget.setAfk(false);
            } else {
                if (context.argsLength() > 0) {
                    aaTarget.setAfkWithMessage(context.getJoinedStrings(0));
                } else {
                    aaTarget.setAfk(true);
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
        }
    }

    /**
     * Command to toggle AFK mode for the specified player.
     *
     * @param context The context in which the command was executed.
     * @param sender  The executor of the command.
     * @throws CommandException
     */
    @Command(aliases = {"setafk, safk"},
            desc = "Toggles AFK mode for the specified player.",
            anyFlags = false,
            help = "This command enables AFK mode (if it is disabled) or disables AFK mode (if it is enabled) for the specified player.",
            min = 1,
            usage = "<player> [message]"
    )
    @Console
    public void setOtherAfkCommand(@Nonnull final CommandContext context, @Nonnull final CommandSender sender) throws CommandException {
        Preconditions.checkNotNull(sender, "setOtherAfkCommand() got null CommandSender");

        //  Find the target
        org.bukkit.entity.Player target = null;
        try {
            target = Preconditions.checkNotNull(Bukkit.getPlayer(Preconditions.checkNotNull(context, "setOtherAfkCommand() got null CommandContext").getString(0)));
        } catch (NullPointerException exception) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
        }

        //  Act on the target
        Player aaTarget = Preconditions.checkNotNull(AlertAFK.plugin.getPlayer(target.getName()), "setOtherAfkCommand() found null AAPlayer: %s", target.getDisplayName());
        if (aaTarget.isAfk()) {
            aaTarget.setAfk(false);
            sender.sendMessage(ChatColor.YELLOW + "You set " + target.getDisplayName() + ChatColor.RESET + ChatColor.YELLOW + " as no longer AFK.");
        } else {
            if (context.argsLength() > 0) {
                aaTarget.setAfkWithMessage(context.getJoinedStrings(1));
            } else {
                aaTarget.setAfk(true);
            }
            sender.sendMessage(ChatColor.YELLOW + "You set " + target.getDisplayName() + ChatColor.RESET + ChatColor.YELLOW + " as AFK.");
        }
    }
}
