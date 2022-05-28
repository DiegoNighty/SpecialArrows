package com.github.diegonighty.specialarrows.effect.particle;

import com.github.diegonighty.specialarrows.util.ParticlePlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.*;

public class WaterCircleEffect implements Effect {
	@Override
	public Runnable create(Location origin) {
		return new BukkitRunnable() {
			@Override
			public void run() {
				int points = 30;
				double radius = 1.2d;

				for (int i = 0; i < points; i++) {
					double angle = 2 * PI * i / points;
					double x = radius * sin(angle);
					double z = radius * cos(angle);

					origin.add(x, 0, z);
					ParticlePlayer.play(Particle.DRIP_WATER, origin, 6);
					origin.subtract(x, 0, z);
				}
			}
		};
	}
}
