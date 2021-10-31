package com.hp.audio.commands

import com.hp.audio.Main
import com.hp.audio.socket.packets.packets.PacketNewSession
import com.hp.audio.socket.packets.packets.PacketRemoveSession
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import net.md_5.bungee.api.chat.HoverEvent

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.hover.content.Text


class AudioCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        sender.sendMessage("Audio command, under construction.")

        if (sender is Player) {

            if (args.isNotEmpty()) {

                when (args[0]) {
                    "connect" -> Main.instance.audioSocket.connect()
                    "stop" -> Main.instance.audioSocket.close()
                    "pause" -> Main.instance.updaterEnabled = !Main.instance.updaterEnabled
                    "error" -> Main.instance.audioSocket.send("Kaas")
                    "map" -> sender.sendMessage(Main.instance.sessionCreator.getSessions())
                }

            } else {
                val packet = PacketNewSession(sender)
                Main.packetHandler.sendPacket(PacketRemoveSession(sender))
                Main.packetHandler.sendPacket(packet)
                sender.sendMessage("New session id: ${packet.sessionUUID}")
                val link = "http://161.97.78.89:2000?uuid=${sender.uniqueId}&key=${packet.sessionUUID}"
                val message = TextComponent("[Connect]")
                message.color = ChatColor.of("#19fff7")
                message.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, link)
                message.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Connect to the audio server"))
                sender.spigot().sendMessage(message)
            }

        }

        return false

    }


}