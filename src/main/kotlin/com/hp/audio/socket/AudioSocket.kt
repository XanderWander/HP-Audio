package com.hp.audio.socket

import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.hp.audio.Main
import com.hp.audio.socket.packets.packets.PacketPlayerJoin
import org.bukkit.Bukkit
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class AudioSocket(uri: URI, headers: HashMap<String, String> = hashMapOf()): WebSocketClient(uri, headers) {

    override fun onOpen(handshakedata: ServerHandshake?) {
        Bukkit.broadcastMessage("Open: ${handshakedata?.httpStatusMessage}")
        if (!Main.instance.playerDataSend) {
            val players = arrayListOf<ArrayList<Any>>()
            for (player in Bukkit.getOnlinePlayers()) {
                players.add(arrayListOf(player.uniqueId, player.name, player.address?.hostString.toString()))
            }
            val packet = PacketPlayerJoin(players)
            Main.packetHandler.sendPacket(packet)
            Main.instance.playerDataSend = true
        }
    }

    override fun onMessage(message: String?) {
        try {
            val type = object : TypeToken<ArrayList<Any>>() {}.type
            val obj: ArrayList<Any> = Main.instance.gson.fromJson(message, type)
            Main.instance.packetCheckHandler.receiveAck(obj[0].toString().toDouble().toInt())
            Bukkit.broadcastMessage("Received: $obj")
        } catch (e: Exception) {
            Bukkit.broadcastMessage("Error type: ${e.message}")
            Bukkit.broadcastMessage("Received invalid msg: $message")
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Bukkit.broadcastMessage("Close: $code $reason $remote")
    }

    override fun onError(ex: Exception?) {
        Bukkit.broadcastMessage("Exception: $ex")
    }

}