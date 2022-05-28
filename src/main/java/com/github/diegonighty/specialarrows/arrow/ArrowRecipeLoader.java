package com.github.diegonighty.specialarrows.arrow;

import org.bukkit.Bukkit;

import java.util.Iterator;

public class ArrowRecipeLoader {

	public static void load(ArrowFinder finder) {
		Iterator<SpecialArrow> arrowIterator = finder.iterator();

		while (arrowIterator.hasNext())	{
			SpecialArrow arrow = arrowIterator.next();
			Bukkit.addRecipe(arrow.recipe());
		}
	}

}
