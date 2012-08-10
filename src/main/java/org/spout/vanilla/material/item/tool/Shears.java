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
package org.spout.vanilla.material.item.tool;

import java.util.Random;

import org.spout.api.entity.Entity;
import org.spout.api.event.player.PlayerInteractEvent.Action;
import org.spout.api.inventory.ItemStack;

import org.spout.vanilla.data.drops.flag.ToolTypeFlags;
import org.spout.vanilla.entity.living.creature.passive.Sheep;
import org.spout.vanilla.entity.object.moving.Item;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.util.VanillaPlayerUtil;

<<<<<<<HEAD:src/main/java/org/spout/vanilla/material/item/misc/Shears.java
		=======
		>>>>>>>origin:src/main/java/org/spout/vanilla/material/item/tool/Shears.java

public class Shears extends Tool {
	private Random rand = new Random();

	public Shears(String name, int id, short durability) {
		super(name, id, durability);
		this.addDropFlags(ToolTypeFlags.SHEARS);
	}

	@Override
	public void onInteract(Entity entity, Entity other, Action action) {
		if (action == Action.RIGHT_CLICK) {
			if (!(other.getController() instanceof Sheep)) {
				return;
			}

			Sheep sheep = (Sheep) other.getController();
			if (sheep.isSheared() || sheep.getGrowing().isBaby()) {
				System.out.println("Debug");
				return;
			}

			sheep.setSheared(true);
			short col = sheep.getColor().getData();

			other.getWorld().createAndSpawnEntity(other.getPosition(), new Item(new ItemStack(VanillaMaterials.WOOL, col, rand.nextInt(3) + 1), other.getPosition().normalize()));

			if (VanillaPlayerUtil.isSurvival(entity)) {
				VanillaPlayerUtil.getCurrentSlot(entity).addItemData(0, 1);
			}
		}
	}
}
