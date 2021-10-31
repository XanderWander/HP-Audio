package com.hp.audio

import com.google.gson.Gson
import com.hp.audio.commands.AudioCommand
import com.hp.audio.data.SessionCreator
import com.hp.audio.events.ConnectionEvent
import com.hp.audio.handlers.PlayerHandler
import com.hp.audio.handlers.UpdateHandler
import com.hp.audio.socket.AudioSocket
import com.hp.audio.socket.packets.PacketCheckHandler
import com.hp.audio.socket.packets.PacketHandler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.net.URI

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
        lateinit var packetHandler: PacketHandler
    }

    var audioSocket = AudioSocket(URI("ws://161.97.78.89:8083"))
    val playerHandler = PlayerHandler()
    val sessionCreator = SessionCreator()
    val packetCheckHandler = PacketCheckHandler()
    val gson = Gson()

    var playerDataSend = false
    var updaterEnabled = true

    lateinit var updateHandler: UpdateHandler

    override fun onEnable() {

        instance = this
        packetHandler = PacketHandler()
        updateHandler = UpdateHandler()

        registerEvents()
        registerCommands()

        playerHandler.register(Bukkit.getOnlinePlayers())

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {
        if (audioSocket.isOpen) {
            audioSocket.send("Server shutting down, goodbye!")
            audioSocket.closeConnection(0, "Server shutting down, Goodbye!")
        }
        logger.info("${description.name} has been disabled.")
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(ConnectionEvent(), this)
    }

    private fun registerCommands() {
        getCommand("audio")?.setExecutor(AudioCommand())
    }

}