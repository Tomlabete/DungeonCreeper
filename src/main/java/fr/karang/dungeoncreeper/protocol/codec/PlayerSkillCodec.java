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
package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import fr.karang.dungeoncreeper.protocol.message.PlayerSkillMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import org.spout.api.protocol.MessageCodec;

public class PlayerSkillCodec extends MessageCodec<PlayerSkillMessage> {
	public PlayerSkillCodec() {
		super(PlayerSkillMessage.class, 0x13);
	}

	@Override
	public PlayerSkillMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readInt();
		boolean endCooldown = buffer.readByte() != 0;
		return new PlayerSkillMessage(id, endCooldown);
	}

	@Override
	public ChannelBuffer encode(PlayerSkillMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getSkill());
		buffer.writeByte(message.getEndCoolDown() ? 1 : 0);
		return buffer;
	}
}
