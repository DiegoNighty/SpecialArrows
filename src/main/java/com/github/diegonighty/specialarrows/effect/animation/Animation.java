package com.github.diegonighty.specialarrows.effect.animation;

import org.bukkit.entity.Player;

public interface Animation {

	static Animation experienceBar() {
		return new ExperienceBarAnimation();
	}

	void display(Player player, int duration);

}
