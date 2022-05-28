package com.github.diegonighty.specialarrows.arrow.types;

import com.github.diegonighty.specialarrows.arrow.IdentifiableSpecialArrow;
import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import com.github.diegonighty.specialarrows.effect.particle.Effect;
import com.github.diegonighty.specialarrows.util.Collections3;
import com.github.diegonighty.specialarrows.util.Entities;
import com.github.diegonighty.specialarrows.util.SoundPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Objects;

public class FishArrow extends IdentifiableSpecialArrow {

	public FishArrow(ItemStack itemStack, Recipe recipe) {
		super(Objects.requireNonNull(itemStack.getItemMeta()).getCustomModelData(), itemStack, recipe);
	}

	@Override
	public void interceptHit(LaunchContext context) {
		Entity damaged = context.getDamaged();

		if (damaged == null || Entities.isBoss(damaged) || Entities.isPlayer(damaged)) {
			return;
		}

		Effect.waterCircle().display(damaged.getLocation());
		damaged.remove();

		Entity replaced = Entities.spawn(damaged, Collections3.peekRandom(Entities.fishes()));
		SoundPlayer.play(Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED, replaced.getLocation());
	}
}
