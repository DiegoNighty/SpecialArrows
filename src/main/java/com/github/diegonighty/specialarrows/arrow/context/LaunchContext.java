package com.github.diegonighty.specialarrows.arrow.context;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Data(staticConstructor = "create")
public class LaunchContext {

	private final AbstractArrow arrow;
	private final @Nullable Entity damaged;
	private final Player sender;
	private final @Nullable Location hit;

	public enum Interceptor {
		LAUNCH,
		HIT,
		KILL
	}

}
