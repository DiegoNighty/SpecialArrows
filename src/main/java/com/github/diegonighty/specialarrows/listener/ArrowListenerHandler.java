package com.github.diegonighty.specialarrows.listener;

import com.github.diegonighty.specialarrows.arrow.ArrowFinder;
import com.github.diegonighty.specialarrows.arrow.PlayerArrowMemory;
import com.github.diegonighty.specialarrows.arrow.SpecialArrow;
import com.github.diegonighty.specialarrows.arrow.context.LaunchContext;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

@AllArgsConstructor
public class ArrowListenerHandler implements Listener {

	private final ArrowFinder arrowFinder;
	private final PlayerArrowMemory arrowMemory;

	@EventHandler
	public void onLaunch(EntityShootBowEvent event) {
		ItemStack arrow = event.getConsumable();
		if (arrow == null || arrow.getItemMeta() == null) {
			return;
		}

		AbstractArrow projectile = (AbstractArrow) event.getProjectile();
		ItemMeta meta = arrow.getItemMeta();
		if (!meta.hasCustomModelData()) {
			return;
		}

		int id = meta.getCustomModelData();
		Entity shooter = (Entity) projectile.getShooter();
		if (shooter == null) return;

		arrowMemory.memorize(shooter.getUniqueId(), id);

		handleEvent(
				event.getEntity(),
				projectile,
				null,
				LaunchContext.Interceptor.LAUNCH
		);
	}

	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		Entity attackerEntity = event.getEntity();
		Entity victimEntity = event.getHitEntity();

		if (!(attackerEntity instanceof AbstractArrow)) {
			return;
		}

		AbstractArrow arrow = (AbstractArrow) attackerEntity;

		handleEvent(
				(Entity) arrow.getShooter(),
				arrow,
				victimEntity,
				LaunchContext.Interceptor.HIT
		);

		if (victimEntity == null) {
			arrowMemory.forget(getShooterId(arrow));
		}
	}

	@EventHandler
	public void onKill(EntityDamageByEntityEvent event) {
		Entity attackerEntity = event.getDamager();
		Entity victimEntity = event.getEntity();

		if (!(attackerEntity instanceof AbstractArrow && victimEntity instanceof LivingEntity)) {
			return;
		}

		AbstractArrow arrow = (AbstractArrow) attackerEntity;
		LivingEntity victim = (LivingEntity) victimEntity;

		if (event.getDamage() >= victim.getHealth()) {
			handleEvent(
					(Entity) arrow.getShooter(),
					arrow,
					victimEntity,
					LaunchContext.Interceptor.KILL
			);
		}

		arrowMemory.forget(getShooterId(arrow));
	}

	private void handleEvent(
			Entity shooter,
			AbstractArrow arrowEntity,
			Entity damaged,
			LaunchContext.Interceptor interceptor
	) {
		if (!(shooter instanceof Player)) {
			return;
		}

		Integer id = getId(arrowEntity);

		if (id == null) {
			return;
		}

		SpecialArrow specialArrow = arrowFinder.find(id);
		LaunchContext context = LaunchContext.create(
				arrowEntity,
				damaged,
				(Player) shooter,
				fromAttached(arrowEntity.getAttachedBlock())
		);

		handleInterceptor(specialArrow, interceptor, context);
	}

	private void handleInterceptor(SpecialArrow arrow, LaunchContext.Interceptor interceptor, LaunchContext context) {
		switch (interceptor) {
			case LAUNCH:
				arrow.interceptLaunch(context);
				break;
			case HIT:
				arrow.interceptHit(context);
				break;
			case KILL:
				arrow.interceptKill(context);
				break;
		}
	}
	private Location fromAttached(Block block) {
		return block == null ? null : block.getLocation();
	}

	private UUID getShooterId(AbstractArrow arrow) {
		Entity shooter = (Entity) arrow.getShooter();
		return shooter == null ? null : shooter.getUniqueId();
	}
	private Integer getId(AbstractArrow arrow) {
		return arrowMemory.memorize(getShooterId(arrow));
	}

}
