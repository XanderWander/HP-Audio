package com.hp.audio.socket.packets.packets

import com.hp.audio.Main
import com.hp.audio.socket.packets.Packet
import com.hp.audio.socket.packets.SimplePacketBuilder
import org.bukkit.entity.Player
import kotlin.collections.ArrayList

class PacketNewSession(private val player: Player): Packet {

    override val id = Main.packetHandler.newID()
    val sessionUUID = Main.instance.sessionCreator.getNewSession(player)

    override fun data(): ArrayList<Any> {
        return SimplePacketBuilder.buildPacketData(id, 1, 0, player.uniqueId.toString(), sessionUUID)
    }

}