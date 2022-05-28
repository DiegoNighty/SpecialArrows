package com.github.diegonighty.specialarrows.util;

import com.github.diegonighty.specialarrows.arrow.ArrowFinder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public final class Configuration extends YamlConfiguration {

	private final Plugin plugin;
	private final String fileName;
	private final File file;

	public Configuration(Plugin plugin, File file, String fileName) {
		requireNonNull(plugin);
		requireNonNull(fileName, "File name cannot be null");
		requireNonNull(file);

		this.plugin = plugin;
		this.fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
		this.file = new File(file, fileName);

		saveDef();
		loadFile();
	}

	public Configuration(Plugin plugin, String fileName) {
		this(plugin, plugin.getDataFolder(), fileName);
	}

	@Override
	public String getString(String path) {
		return formatString(super.getString(path, path));
	}

	@Override
	public List<String> getStringList(String path) {
		return super.getStringList(path).stream()
				.map(this::formatString)
				.collect(Collectors.toList());
	}

	public ConfigurationSection getSection(@NotNull String path) {
		return Optional.ofNullable(super.getConfigurationSection(path)).orElse(getParent());
	}

	public List<ItemStack> fromMaterialList(String path) {
		return getStringList(path).stream()
				.map(Material::matchMaterial)
				.filter(Objects::nonNull)
				.map(ItemStack::new)
				.collect(Collectors.toList());
	}

	public Recipe parseRecipe(ConfigurationSection section, ArrowFinder finder, ItemStack result, String key) {
		ConfigurationSection ingredients = section.getConfigurationSection("ingredients");
		ShapedRecipe recipe = new ShapedRecipe(requireNonNull(NamespacedKey.fromString(key)), result);

		String[] shape = section.getString("shape", "").split(",");
		recipe.shape(shape);

		for (String ingredient : requireNonNull(ingredients).getKeys(false)) {
			recipe.setIngredient(ingredient.charAt(0), find(ingredients.getString(ingredient), finder));
		}

		return recipe;
	}

	private RecipeChoice find(String materialName, ArrowFinder finder) {
		Material material = Material.matchMaterial(materialName);
		return material != null ?
				new RecipeChoice.MaterialChoice(material) :
				new RecipeChoice.ExactChoice(finder.find(Integer.parseInt(materialName)).item());
	}

	private void loadFile() {
		try {
			this.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void saveDef() {
		if (!file.exists()) {
			plugin.saveResource(fileName, false);
		}
	}

	public void save() {
		try {
			save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reloadFile() {
		try {
			load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	private String formatString(final String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}
