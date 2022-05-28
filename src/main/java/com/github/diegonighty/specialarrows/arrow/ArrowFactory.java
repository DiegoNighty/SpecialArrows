package com.github.diegonighty.specialarrows.arrow;

import com.github.diegonighty.specialarrows.util.Configuration;
import com.github.diegonighty.specialarrows.arrow.types.BundleArrow;
import com.github.diegonighty.specialarrows.arrow.types.CrystalArrow;
import com.github.diegonighty.specialarrows.arrow.types.DiamondArrow;
import com.github.diegonighty.specialarrows.arrow.types.FishArrow;
import com.github.diegonighty.specialarrows.arrow.types.InfinityArrow;
import com.github.diegonighty.specialarrows.util.ItemStream;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class ArrowFactory {

	private final Configuration config;
	private final ArrowFinder arrowFinder;

	public SpecialArrow newBundleArrow() {
		ItemStack result = ItemStream.of(config.getSection("bundle-arrow"), Material.ARROW)
				.toBukkit();

		return new BundleArrow(
				result,
				config.getInt("bundle-arrow.multiplier"),
				config.parseRecipe(config.getSection("bundle-arrow.recipe"), arrowFinder, result, "bundle_arrow")
		);
	}

	public SpecialArrow newDiamondArrow() {
		ItemStack result = ItemStream.of(config.getSection("diamond-arrow"), Material.ARROW)
				.toBukkit();

		return new DiamondArrow(
				result,
				config.fromMaterialList("diamond-arrow.drops"),
				config.parseRecipe(config.getSection("diamond-arrow.recipe"), arrowFinder, result, "diamond_arrow")
		);
	}

	public SpecialArrow newCrystalArrow() {
		ItemStack result = ItemStream.of(config.getSection("crystal-arrow"), Material.ARROW)
				.toBukkit();

		return new CrystalArrow(
				result,
				config.getInt("crystal-arrow.multiplier-velocity"),
				config.parseRecipe(config.getSection("crystal-arrow.recipe"), arrowFinder, result, "crystal_arrow")
		);
	}

	public SpecialArrow newFishArrow() {
		ItemStack result = ItemStream.of(config.getSection("fish-arrow"), Material.ARROW)
				.toBukkit();

		return new FishArrow(
				result,
				config.parseRecipe(config.getSection("fish-arrow.recipe"), arrowFinder, result, "fish_arrow")
		);
	}

	public SpecialArrow newInfinityArrow() {
		ItemStack result = ItemStream.of(config.getSection("infinity-arrow"), Material.ARROW)
				.toBukkit();

		return new InfinityArrow(
				result,
				config.getDouble("infinity-arrow.damage"),
				config.getInt("infinity-arrow.delay"),
				config.parseRecipe(config.getSection("infinity-arrow.recipe"), arrowFinder, result, "infinity_arrow")
		);
	}

}
