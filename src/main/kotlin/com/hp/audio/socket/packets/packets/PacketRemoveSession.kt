package com.hp.audio.socket.packets.packets

import com.hp.audio.Main
import com.hp.audio.socket.packets.Packet
import com.hp.audio.socket.packets.SimplePacketBuilder
import org.bukkit.entity.Player

class PacketRemoveSession(private val player: Player): Packet {

    override val id = Main.packetHandler.newID()

    override fun data(): ArrayList<Any> {
        return SimplePacketBuilder.buildPacketData(id, 1, 1, player.uniqueId.toString())
    }

}