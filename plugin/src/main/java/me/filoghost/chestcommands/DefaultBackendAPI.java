/*
 * Copyright (C) filoghost and contributors
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package me.filoghost.chestcommands;

import me.filoghost.chestcommands.api.ConfigurableIcon;
import me.filoghost.chestcommands.api.Menu;
import me.filoghost.chestcommands.api.PlaceholderReplacer;
import me.filoghost.chestcommands.api.StaticIcon;
import me.filoghost.chestcommands.api.internal.BackendAPI;
import me.filoghost.chestcommands.icon.APIConfigurableIcon;
import me.filoghost.chestcommands.icon.APIStaticIcon;
import me.filoghost.chestcommands.menu.APIMenu;
import me.filoghost.chestcommands.menu.InternalMenu;
import me.filoghost.chestcommands.placeholder.PlaceholderManager;
import me.filoghost.fcommons.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class DefaultBackendAPI extends BackendAPI {

    @Override
    public boolean pluginMenuExists(@NotNull String menuFileName) {
        Preconditions.notNull(menuFileName, "menuFileName");

        return ChestCommands.getMenuManager().getMenuByFileName(menuFileName) != null;
    }

    @Override
    public boolean openPluginMenu(@NotNull Player player, @NotNull String menuFileName) {
        Preconditions.notNull(player, "player");
        Preconditions.notNull(menuFileName, "menuFileName");

        InternalMenu menu = ChestCommands.getMenuManager().getMenuByFileName(menuFileName);

        if (menu != null) {
            menu.open(player);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @NotNull ConfigurableIcon createConfigurableIcon(@NotNull Material material) {
        return new APIConfigurableIcon(material);
    }

    @Override
    public @NotNull Menu createMenu(@NotNull Plugin owner, @NotNull String title, int rows) {
        return new APIMenu(owner, title, rows);
    }

    @Override
    public @NotNull StaticIcon createStaticIcon(@NotNull ItemStack itemStack) {
        return new APIStaticIcon(itemStack);
    }

    @Override
    public void registerPlaceholder(@NotNull Plugin plugin, @NotNull String identifier, @NotNull PlaceholderReplacer placeholderReplacer) {
        PlaceholderManager.registerPluginPlaceholder(plugin, identifier, placeholderReplacer);
    }

}
