package com.hp.audio.socket.packets.packets

import com.hp.audio.Main
import com.hp.audio.socket.packets.Packet
import com.hp.audio.socket.packets.SimplePacketBuilder
import org.bukkit.entity.Player
import kotlin.math.round

class PacketUpdatePlayerLocation(private val players: ArrayList<Player>): Packet {

    override val id = Main.packetHandler.newID()

    override fun data(): ArrayList<Any> {

        val data = arrayListOf<ArrayList<Any>>()
        for (player in players) {
            data.add(playerData(player))
        }
        return SimplePacketBuilder.buildPacketData(id, 5, 2, data)
    }

    private fun playerData(player: Player): ArrayList<Any> {
        val playerData = arrayListOf<Any>()
        playerData.add(player.uniqueId.toString())
        playerData.add(player.location.x.round(2))
        playerData.add(player.location.y.round(2))
        playerData.add(player.location.z.round(2))
        return playerData
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}