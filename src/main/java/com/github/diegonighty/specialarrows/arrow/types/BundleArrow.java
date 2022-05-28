package com.github.diegonighty.specialarrows.arrow.types;

import com.github.diegonighty.specialarrows.arrow.IdentifiableSpecialArrow;
import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Objects;

public class BundleArrow extends IdentifiableSpecialArrow {
	private final int multiplier;

	public BundleArrow(ItemStack itemStack, int multiplier, Recipe recipe) {
		super(Objects.requireNonNull(itemStack.getItemMeta()).getCustomModelData(), itemStack, recipe);
		this.multiplier = multiplier;
	}

	@Override
	public void interceptLaunch(LaunchContext context) {
		Player sender = context.getSender();
		AbstractArrow arrow = context.getArrow();

		for (int i = 0; i < multiplier; i++) {
			sender.launchProjectile(arrow.getClass());
		}
	}
}
