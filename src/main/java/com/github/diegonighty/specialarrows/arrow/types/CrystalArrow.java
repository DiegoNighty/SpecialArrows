package com.github.diegonighty.specialarrows.arrow.types;

import com.github.diegonighty.specialarrows.arrow.IdentifiableSpecialArrow;
import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Objects;

public class CrystalArrow extends IdentifiableSpecialArrow {

	private final int velocity;

	public CrystalArrow(ItemStack itemStack, int velocity, Recipe recipe) {
		super(Objects.requireNonNull(itemStack.getItemMeta()).getCustomModelData(), itemStack, recipe);
		this.velocity = velocity;
	}

	@Override
	public void interceptLaunch(LaunchContext context) {
		AbstractArrow arrow = context.getArrow();
		arrow.setGravity(false);
		arrow.setVelocity(arrow.getVelocity().multiply(velocity));
	}
}
