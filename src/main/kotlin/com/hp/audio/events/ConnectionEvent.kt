package com.hp.audio.events

import com.hp.audio.Main
import com.hp.audio.socket.packets.PacketHandler
import com.hp.audio.socket.packets.packets.PacketPlayerJoin
import com.hp.audio.socket.packets.packets.PacketPlayerQuit
import com.hp.audio.socket.packets.packets.PacketRemoveSession
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class ConnectionEvent: Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        Main.instance.playerHandler.register(e.player)
        Main.packetHandler.sendPacket(PacketPlayerJoin(e.player))
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        Main.instance.playerHandler.remove(e.player)
        Main.packetHandler.sendPacket(PacketRemoveSession(e.player))
        Main.packetHandler.sendPacket(PacketPlayerQuit(e.player))
    }

}