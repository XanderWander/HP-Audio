package com.hp.audio.socket.packets.packets

import com.hp.audio.Main
import com.hp.audio.socket.packets.Packet
import com.hp.audio.socket.packets.SimplePacketBuilder

class PacketUpdateMillis: Packet {

    override val id = Main.packetHandler.newID()

    override fun data(): ArrayList<Any> {
        val millis = System.currentTimeMillis()
        return SimplePacketBuilder.buildPacketData(id, 4, 0, millis)
    }

}