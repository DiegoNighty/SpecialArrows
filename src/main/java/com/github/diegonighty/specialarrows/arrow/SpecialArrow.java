package com.github.diegonighty.specialarrows.arrow;

import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public interface SpecialArrow {

	int id();

	ItemStack item();

	Recipe recipe();

	void interceptLaunch(LaunchContext context);

	void interceptHit(LaunchContext context);

	void interceptKill(LaunchContext context);

}
