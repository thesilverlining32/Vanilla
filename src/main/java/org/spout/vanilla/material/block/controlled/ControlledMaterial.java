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
package org.spout.vanilla.material.block.controlled;

import java.util.Set;

import org.spout.api.geo.cuboid.Block;
import org.spout.api.material.block.BlockFace;
import org.spout.api.math.Vector3;

import org.spout.vanilla.data.drops.flag.DropFlagSingle;
import org.spout.vanilla.entity.VanillaControllerType;
import org.spout.vanilla.material.block.Solid;

<<<<<<<HEAD
		=======
		>>>>>>>origin

public abstract class ControlledMaterial extends Solid {
	private final VanillaControllerType type;

	public ControlledMaterial(VanillaControllerType type, String name, int id) {
		super(name, id);
		this.type = type;
		this.setController(type);
	}

	@Override
	public void onDestroy(Block block, Set<DropFlagSingle> dropFlags) {
		block.getWorld().setBlockController(block.getX(), block.getY(), block.getZ(), null);
		super.onDestroy(block, dropFlags);
	}

	@Override
	public boolean onPlacement(Block block, short data, BlockFace against, Vector3 clickedPos, boolean isClickedBlock) {
		super.onPlacement(block, data, against, clickedPos, isClickedBlock);
		block.getWorld().createAndSpawnEntity(block.getPosition(), type.createController());
		return true;
	}
}
