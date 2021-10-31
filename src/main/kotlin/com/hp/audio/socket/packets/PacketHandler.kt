package com.hp.audio.socket.packets

import com.google.gson.Gson
import com.hp.audio.Main
import org.bukkit.Bukkit

class PacketHandler {

    private var nextID = 0
    private val gson = Gson()

    fun sendPacket(packet: Packet) {
        val data = gson.toJson(packet.data())
        Main.instance.packetCheckHandler.newSendPacket(packet)
        Main.instance.audioSocket.send(data)
    }

    fun newID(): Int {
        if (nextID > 100000) { nextID = 0 }
        nextID += 1
        return nextID
    }

}