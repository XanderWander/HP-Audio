package com.hp.audio.socket.packets.packets

import com.hp.audio.Main
import com.hp.audio.socket.packets.Packet
import com.hp.audio.socket.packets.SimplePacketBuilder
import org.bukkit.entity.Player

class PacketPlayerJoin: Packet {

    private var players = arrayListOf<ArrayList<Any>>()

    constructor(player: Player) {
        players.add(arrayListOf(player.uniqueId, player.name, player.address.toString()))
    }

    constructor(players: ArrayList<ArrayList<Any>>) {
        this.players = players
    }

    override val id = Main.packetHandler.newID()

    override fun data(): ArrayList<Any> {
        return SimplePacketBuilder.buildPacketData(id, 5, 0, players)
    }

}