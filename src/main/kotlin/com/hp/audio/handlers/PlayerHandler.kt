package com.hp.audio.handlers

import org.bukkit.entity.Player

class PlayerHandler {

    private val playerList = arrayListOf<Player>()

    fun register(player: Player) { playerList.add(player) }
    fun register(players: Collection<Player>) { playerList.addAll(players) }
    fun remove(player: Player) { playerList.remove(player) }
    fun getPlayers(): ArrayList<Player> { return playerList }
    fun contains(player: Player): Boolean { return playerList.contains(player) }

    fun findPlayer(player: String): Player? {
        for (search in getPlayers())
            if (search.name.toLowerCase().startsWith(player.toLowerCase()))
                return search
        return null
    }

}