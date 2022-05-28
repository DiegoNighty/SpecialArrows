package com.github.diegonighty.specialarrows;

import com.github.diegonighty.specialarrows.arrow.ArrowFactory;
import com.github.diegonighty.specialarrows.arrow.ArrowFinder;
import com.github.diegonighty.specialarrows.arrow.ArrowRecipeLoader;
import com.github.diegonighty.specialarrows.arrow.PlayerArrowMemory;
import com.github.diegonighty.specialarrows.listener.ArrowListenerHandler;
import com.github.diegonighty.specialarrows.util.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ArrowPlugin extends JavaPlugin {

	private final ArrowFinder arrowFinder = new ArrowFinder();
	private final PlayerArrowMemory arrowMemory = new PlayerArrowMemory();
	private ArrowFactory arrowFactory;

	@Override
	public void onEnable() {
		arrowFactory = new ArrowFactory(new Configuration(this, "config.yml"), arrowFinder);
		loadArrows();

		ArrowRecipeLoader.load(arrowFinder);
		Bukkit.getPluginManager().registerEvents(new ArrowListenerHandler(arrowFinder, arrowMemory), this);
	}

	private void loadArrows() {
		arrowFinder.loadArrow(arrowFactory.newBundleArrow());
		arrowFinder.loadArrow(arrowFactory.newDiamondArrow());
		arrowFinder.loadArrow(arrowFactory.newFishArrow());
		arrowFinder.loadArrow(arrowFactory.newCrystalArrow());
		arrowFinder.loadArrow(arrowFactory.newInfinityArrow());
	}

}
