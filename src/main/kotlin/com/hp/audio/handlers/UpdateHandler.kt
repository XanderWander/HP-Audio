package com.hp.audio.handlers

import com.hp.audio.Main
import com.hp.audio.socket.AudioSocket
import com.hp.audio.socket.packets.PacketHandler
import com.hp.audio.socket.packets.packets.PacketUpdateMillis
import com.hp.audio.socket.packets.packets.PacketUpdatePlayerLocation
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import java.net.URI

class UpdateHandler: BukkitRunnable() {

    private val password = "WvDAfDgf9CDnyAcT"
    private var timout = 0
    private var reboot = false

    init {
        this.runTaskTimer(Main.instance, 20L, 1L)
        reconnect()
    }

    override fun run() {

        if (!Main.instance.updaterEnabled) return
        if (timout > 0) {
            timout--
        } else {
            if (Main.instance.audioSocket.isClosed) {
                if (reboot) {
                    Main.instance.logger.warning("Socket connection failed again, timeout 10 seconds")
                    timout = 40
                    reboot = false
                } else {
                    reconnect()
                    reboot = true
                }
            } else {
                val packetUpdateLocations = PacketUpdatePlayerLocation(Main.instance.playerHandler.getPlayers())
                Main.packetHandler.sendPacket(PacketUpdateMillis())
                Main.packetHandler.sendPacket(packetUpdateLocations)
            }
        }
    }

    private fun reconnect() {
        Main.instance.audioSocket = AudioSocket(URI("ws://161.97.78.89:8083"))
        Main.instance.audioSocket.addHeader("key", password)
        Main.instance.audioSocket.connect()
        Main.instance.logger.warning("Error! Socket had to be restarted")
    }

}