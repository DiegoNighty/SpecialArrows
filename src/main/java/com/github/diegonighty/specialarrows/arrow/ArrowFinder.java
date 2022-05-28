package com.github.diegonighty.specialarrows.arrow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArrowFinder implements Iterable<SpecialArrow> {

	private final Map<Integer, SpecialArrow> arrows = new HashMap<>();

	public void loadArrow(SpecialArrow arrow) {
		arrows.put(arrow.id(), arrow);
	}

	public SpecialArrow find(int arrowId) {
		return arrows.get(arrowId);
	}

	public Iterator<SpecialArrow> iterator() {
		return arrows.values().iterator();
	}

}
