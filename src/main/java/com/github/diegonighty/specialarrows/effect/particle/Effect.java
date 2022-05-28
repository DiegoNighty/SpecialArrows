package com.github.diegonighty.specialarrows.effect.particle;

import com.github.diegonighty.specialarrows.concurrent.BukkitExecutorProvider;
import org.bukkit.Location;

public interface Effect {

	static Effect waterCircle() {
		return new WaterCircleEffect();
	}

	Runnable create(Location origin);

	default void display(Location origin) {
		BukkitExecutorProvider.get().runAsync(create(origin));
	}

}
