package com.github.diegonighty.specialarrows.concurrent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class BukkitExecutor implements Executor {

	private final Plugin plugin;

	public BukkitExecutor(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void execute(@NotNull Runnable command) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, command);
	}

	public void runAsync(Runnable command) {
		Bukkit.getScheduler().runTaskAsynchronously(
				plugin,
				command
		);
	}

	public int runTaskTimer(Runnable command, long delay, long period) {
		return Bukkit.getScheduler().runTaskTimer(plugin, command, delay, period).getTaskId();
	}

	public void executeTaskWithDelay(Runnable command, long ticks) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, command, ticks);
	}

	public void cancelTask(int id) {
		Bukkit.getScheduler().cancelTask(id);
	}
}

