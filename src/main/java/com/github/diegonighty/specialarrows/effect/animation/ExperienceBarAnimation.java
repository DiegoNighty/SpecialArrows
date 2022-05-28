package com.github.diegonighty.specialarrows.effect.animation;

import com.github.diegonighty.specialarrows.concurrent.BukkitExecutor;
import com.github.diegonighty.specialarrows.concurrent.BukkitExecutorProvider;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ExperienceBarAnimation implements Animation {

	private final static BukkitExecutor EXECUTOR = BukkitExecutorProvider.get();

	@Override
	public void display(Player player, int duration) {
		final int originalLevel = player.getLevel();

		int id = EXECUTOR.runTaskTimer(
				new BukkitRunnable() {
					@Override
					public void run() {
						if (player.getLevel() <= 0 || player.getLevel() > duration) {
							player.setLevel(duration);
						}

						player.setLevel(player.getLevel() - 1);
					}
				}, 0, 20
		);

		EXECUTOR.executeTaskWithDelay(() -> {
			EXECUTOR.cancelTask(id);
			player.setLevel(originalLevel);
		}, duration * 20L);


	}
}
