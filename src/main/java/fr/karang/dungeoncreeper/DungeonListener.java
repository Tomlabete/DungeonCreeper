/*
 * This file is part of DungeonCreeper.
 *
 * Copyright (c) 2012-2012, Karang <http://arthur.hennequin.free.fr/>
 * DungeonCreeper is licensed under the SpoutDev License Version 1.
 *
 * DungeonCreeper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * DungeonCreeper is distributed in the hope that it will be useful,
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
package fr.karang.dungeoncreeper;

import fr.karang.dungeoncreeper.component.entity.creature.Imp;
import fr.karang.dungeoncreeper.gui.HUD;
import fr.karang.dungeoncreeper.player.DungeonPlayer;
import fr.karang.dungeoncreeper.render.DungeonResources;

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.component.components.CameraComponent;
import org.spout.api.component.components.PhysicsComponent;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.server.ClientEnableEvent;

import com.bulletphysics.collision.shapes.BoxShape;

public class DungeonListener implements Listener {
	private DungeonCreeper plugin;

	public DungeonListener(DungeonCreeper plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		plugin.getLobby().playerJoin(event.getPlayer());
	}

	@EventHandler
	public void onClientEnable(ClientEnableEvent event) {
		final Player player = ((Client) Spout.getEngine()).getActivePlayer();
		
		DungeonResources.init();

		player.add(Imp.class);
		player.add(DungeonPlayer.class);
		
		/*PhysicsComponent physics = player.add(PhysicsComponent.class);
		physics.setMass(1.f);
		physics.setCollisionShape(new BoxShape(0.5f, 0.5f, 0.5f));*/

		final HUD hud = new HUD(player);
		((Client) Spout.getEngine()).getScreenStack().openScreen(hud);

		CameraComponent camera = player.get(CameraComponent.class);
		camera.setScale(0.5f);
		camera.setSpeed(10f);
	}
}
