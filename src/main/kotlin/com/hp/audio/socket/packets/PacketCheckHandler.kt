package com.hp.audio.socket.packets

import com.hp.audio.Main
import org.bukkit.Bukkit

class PacketCheckHandler {

    private val packetList = arrayListOf<Packet>()

    fun newSendPacket(packet: Packet) {
        //packetList.add(packet)
    }

    fun receiveAck(id: Int) {
//        var packet: Packet? = null
//        for (listPacket in packetList) {
//            if (listPacket.id == id) {
//                packet = listPacket
//            }
//        }
//        if (packet != null) {
//            packetList.remove(packet)
//        } else {
//            Main.instance.logger.warning("Received acknowledgement of unsent packet id: $id")
//        }
//        Bukkit.broadcastMessage("Total not-ack list: ${packetList.size}")
    }

}