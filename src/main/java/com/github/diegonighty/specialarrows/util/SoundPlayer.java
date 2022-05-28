package com.github.diegonighty.specialarrows.util;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SoundPlayer {

	static final int VOLUME = 100;
	static final int PITCH = 20;

	public static void play(Sound sound, Player player) {
		player.playSound(player.getLocation(), sound, VOLUME, PITCH);
	}

	public static void play(Sound sound, Location location) {
		Objects.requireNonNull(location.getWorld()).playSound(location, sound, VOLUME, PITCH);
	}

}
