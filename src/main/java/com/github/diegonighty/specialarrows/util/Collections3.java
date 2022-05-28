package com.github.diegonighty.specialarrows.util;

import java.util.List;
import java.util.Random;

public class Collections3 {

	private final static Random RANDOM = new Random();

	public static <T> T peekRandom(List<T> coll) {
		return coll.get(RANDOM.nextInt(coll.size() - 1));
	}

}
