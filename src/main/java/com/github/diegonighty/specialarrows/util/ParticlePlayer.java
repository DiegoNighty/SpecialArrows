package com.github.diegonighty.specialarrows.util;

import org.bukkit.Location;
import org.bukkit.Particle;

import java.util.Objects;

public class ParticlePlayer {

	public static void play(Particle particle, Location origin, int count) {
		Objects.requireNonNull(origin.getWorld()).spawnParticle(particle, origin, count);
	}

}
