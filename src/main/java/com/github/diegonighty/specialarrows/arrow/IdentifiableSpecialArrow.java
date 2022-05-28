package com.github.diegonighty.specialarrows.arrow;

import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import lombok.AllArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

@AllArgsConstructor
public abstract class IdentifiableSpecialArrow implements SpecialArrow {

	protected final int id;
	protected final ItemStack itemStack;

	protected final Recipe recipe;

	@Override
	public Recipe recipe() {
		return recipe;
	}

	@Override
	public ItemStack item() {
		return itemStack;
	}

	@Override
	public void interceptHit(LaunchContext context) {
		//empty body
	}

	@Override
	public void interceptLaunch(LaunchContext context) {
		//empty body
	}

	@Override
	public void interceptKill(LaunchContext context) {
		//empty body
	}

	@Override
	public int id() {
		return id;
	}
}
