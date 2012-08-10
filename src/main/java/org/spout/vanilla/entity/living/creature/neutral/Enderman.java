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
package org.spout.vanilla.entity.living.creature.neutral;

import org.spout.api.collision.BoundingBox;
import org.spout.api.collision.CollisionModel;
import org.spout.api.data.Data;
import org.spout.api.inventory.ItemStack;

import org.spout.vanilla.entity.VanillaControllerTypes;
import org.spout.vanilla.entity.living.Creature;
import org.spout.vanilla.entity.living.creature.Neutral;
import org.spout.vanilla.material.VanillaMaterials;

<<<<<<<HEAD:src/main/java/org/spout/vanilla/entity/living/creature/neutral/Enderman.java
		=======
		>>>>>>>origin:src/main/java/org/spout/vanilla/controller/living/creature/neutral/Enderman.java

public class Enderman extends Creature implements Neutral {
	private ItemStack heldItem;
	private ItemStack lastHeldItem;

	public Enderman() {
		super(VanillaControllerTypes.ENDERMAN);
	}

	@Override
	public void onAttached() {
		super.onAttached();
		getHealth().setSpawnHealth(40);
		if (getDataMap().containsKey(Data.HELD_ITEM)) {
			heldItem = getDataMap().get(Data.HELD_ITEM);
		}
		getParent().setCollision(new CollisionModel(new BoundingBox(1, 3, 1, 2, 3, 1)));
		getDrops().addRange(VanillaMaterials.ENDER_PEARL, 1);
	}

	@Override
	public void onSave() {
		super.onSave();
		getDataMap().put(Data.HELD_ITEM, heldItem);
	}

	@Override
	public void finalizeTick() {
		super.finalizeTick();
		this.lastHeldItem = heldItem;
	}

	public ItemStack getPreviouslyHeldItem() {
		return lastHeldItem;
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

	public void setHeldItem(ItemStack heldItem) {
		this.heldItem = heldItem;
	}
}
