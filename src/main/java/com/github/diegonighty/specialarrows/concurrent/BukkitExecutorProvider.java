package com.github.diegonighty.specialarrows.concurrent;

import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitExecutorProvider {

	private static BukkitExecutor executor;

	public static synchronized BukkitExecutor get() {
		if (executor == null) {
			executor = new BukkitExecutor(JavaPlugin.getProvidingPlugin(BukkitExecutorProvider.class));
		}

		return executor;
	}
}
