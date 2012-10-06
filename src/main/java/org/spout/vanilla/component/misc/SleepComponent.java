/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.component.misc;

import org.spout.api.component.components.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;

import org.spout.vanilla.component.world.VanillaSky;
import org.spout.vanilla.data.Animation;
import org.spout.vanilla.data.Times;
import org.spout.vanilla.event.entity.EntityAnimationEvent;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.block.misc.BedBlock;

public class SleepComponent extends EntityComponent {
	private final float sleepSeconds = 5;
	private float sleepTimer = sleepSeconds;
	private boolean sleeping;
	private Player player;
	private Block bed;

	public Block getBed() {
		return bed;
	}

	public void setBed(Block bed) {
		this.bed = bed;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	@Override
	public void onAttached() {
		if (!(getHolder() instanceof Player)) {
			throw new IllegalStateException("SleepComponent can only be attached to Player entities.");
		}
		player = (Player) getHolder();
	}

	@Override
	public void onTick(float dt) {
		// Decrement sleep timer if sleeping
		if (sleeping) {
			sleepTimer -= dt;
		} else {
			sleepTimer = sleepSeconds;
		}

		// If sleep timer if finished try and skip to day
		if (sleepTimer <= 0) {
			World world = getHolder().getWorld();
			for (Player player : world.getPlayers()) {
				if (!player.add(SleepComponent.class).isSleeping()) {
					return;
				}
			}
			world.getComponentHolder().get(VanillaSky.class).setTime(Times.DAWN.getTime());
			player.getNetwork().callProtocolEvent(new EntityAnimationEvent(player, Animation.LEAVE_BED));
		}
	}
}
