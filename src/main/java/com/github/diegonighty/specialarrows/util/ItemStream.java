package com.github.diegonighty.specialarrows.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemStream {

	private ItemStack backingItem;
	private final List<Consumer<ItemMeta>> operations = new ArrayList<>();

	public ItemStack toBukkit() {
		ItemMeta meta = backingItem.getItemMeta();
		operations.forEach(withMeta -> withMeta.accept(meta));

		backingItem.setItemMeta(meta);
		return backingItem;
	}

	public ItemStream name(String name) {
		return addOperation(itemMeta -> itemMeta.setDisplayName(name));
	}

	public ItemStream data(int data) {
		return addOperation(itemMeta -> itemMeta.setCustomModelData(data));
	}

	public ItemStream lore(Collection<String> lore) {
		return addOperation(itemMeta -> itemMeta.setLore(new ArrayList<>(lore)));
	}

	public ItemStream colorize() {
		return addOperation(itemMeta -> {
			itemMeta.setDisplayName(colorize(itemMeta.getDisplayName()));
			itemMeta.setLore(colorize(itemMeta.getLore()));
		});
	}

	private ItemStream addOperation(Consumer<ItemMeta> withMeta) {
		operations.add(withMeta);
		return this;
	}

	public static ItemStream of(Material material) {
		return of(new ItemStack(material));
	}

	public static ItemStream of(ConfigurationSection section, Material material) {
		return of(material)
				.name(section.getString("name"))
				.lore(section.getStringList("lore"))
				.data(section.getInt("data"))
				.colorize();
	}

	public static ItemStream of(ItemStack item) {
		return new ItemStream(item);
	}

	private ItemStream(ItemStack backingItem) {
		this.backingItem = backingItem;
	}

	private List<String> colorize(List<String> texts) {
		return texts.stream()
				.map(this::colorize)
				.collect(Collectors.toList());
	}

	private String colorize(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}
