package com.github.diegonighty.specialarrows.arrow.types;

import com.github.diegonighty.specialarrows.arrow.IdentifiableSpecialArrow;
import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Collection;
import java.util.Objects;

public class DiamondArrow extends IdentifiableSpecialArrow {

	private final Collection<ItemStack> drop;

	public DiamondArrow(ItemStack itemStack, Collection<ItemStack> drop, Recipe recipe) {
		super(Objects.requireNonNull(itemStack.getItemMeta()).getCustomModelData(), itemStack, recipe);
		this.drop = drop;
	}

	@Override
	public void interceptKill(LaunchContext context) {
		Entity hit = context.getDamaged();
		if (hit == null) return;

		World world = hit.getWorld();

		for (ItemStack itemStack : drop) {
			world.dropItem(hit.getLocation(), itemStack);
		}
	}
}
