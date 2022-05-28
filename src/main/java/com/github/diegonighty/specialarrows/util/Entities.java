package com.github.diegonighty.specialarrows.util;

import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;

public class Entities {

	static final List<EntityType> fishes = Lists.newArrayList(
			EntityType.PUFFERFISH,
			EntityType.TROPICAL_FISH,
			EntityType.SALMON,
			EntityType.COD
	);

	static final List<EntityType> bosses = Lists.newArrayList(
			EntityType.WITHER,
			EntityType.ELDER_GUARDIAN,
			EntityType.ENDER_DRAGON
	);

	public static boolean isBoss(Entity entity) {
		return bosses.contains(entity.getType());
	}

	public static boolean isPlayer(Entity entity) {
		return entity.getType() == EntityType.PLAYER;
	}

	public static Entity spawn(Entity toReplace, EntityType replace) {
		Location hit = toReplace.getLocation();

		World world = hit.getWorld();
		if (world == null) return toReplace;

		return world.spawnEntity(hit, replace);
	}

	public static List<EntityType> fishes() {
		return fishes;
	}

}
