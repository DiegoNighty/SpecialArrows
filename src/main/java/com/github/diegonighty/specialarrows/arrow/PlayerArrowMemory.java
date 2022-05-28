package com.github.diegonighty.specialarrows.arrow;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerArrowMemory {

	private final Map<UUID, Integer> arrows = new HashMap<>();

	public void memorize(UUID entityId, Integer arrowId) {
		arrows.put(entityId, arrowId);
	}

	public Integer memorize(UUID entityId) {
		return arrows.get(entityId);
	}

	public void forget(UUID entityId) {
		arrows.remove(entityId);
	}

}
