package com.github.diegonighty.specialarrows.arrow.types;

import com.github.diegonighty.specialarrows.arrow.IdentifiableSpecialArrow;
import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import com.github.diegonighty.specialarrows.concurrent.BukkitExecutor;
import com.github.diegonighty.specialarrows.concurrent.BukkitExecutorProvider;
import com.github.diegonighty.specialarrows.effect.animation.Animation;
import com.github.diegonighty.specialarrows.util.SoundPlayer;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Objects;

public class InfinityArrow extends IdentifiableSpecialArrow {
	private final double damage;
	private final int delay;
	private final BukkitExecutor executor = BukkitExecutorProvider.get();

	public InfinityArrow(ItemStack itemStack, double damage, int delay, Recipe recipe) {
		super(Objects.requireNonNull(itemStack.getItemMeta()).getCustomModelData(), itemStack, recipe);

		this.damage = damage;
		this.delay = delay;
	}

	@Override
	public void interceptLaunch(LaunchContext context) {
		context.getArrow().setDamage(damage);
		Animation.experienceBar().display(context.getSender(), delay);

		executor.executeTaskWithDelay(() -> {
			context.getSender().getInventory().addItem(itemStack);
			SoundPlayer.play(Sound.ENTITY_IRON_GOLEM_REPAIR, context.getSender());
		}, delay * 20L);
	}
}
