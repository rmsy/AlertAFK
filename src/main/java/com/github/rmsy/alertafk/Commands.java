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

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
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
     */
    @Command(
            aliases = {"afk", "away"},
            desc = "Toggles AFK mode",
            anyFlags = false,
            flags = "p:",
            help = "This command enables AFK mode (if it is disabled) or disables AFK mode (if it is enabled).",
            min = 0,
            usage = "<message> [-p <player>]"
    )
    public void afkCommand(@Nonnull final CommandContext context, @Nonnull final CommandSender sender) throws CommandException {
        //  Assuming that CommandManager only sends us commands from Players...
        if (context.hasFlag('p')) {
            t
        }
        org.bukkit.entity.Player target = (org.bukkit.entity.Player) sender;
        Player aaTarget = AlertAFK.plugin.getPlayer(target.getName());

        if (context.argsLength() > 0) {
            if (aaTarget.isAfk()) {
                aaTarget.setAfk(false);
            }
        } else {
            aaTarget.setAfk(!aaTarget.isAfk());
        }
    }
}
