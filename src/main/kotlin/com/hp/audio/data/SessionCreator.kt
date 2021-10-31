package com.hp.audio.data

import org.bukkit.entity.Player
import java.util.*

class SessionCreator {

    private val sessionMap = hashMapOf<Player, UUID>()

    fun getNewSession(player: Player): UUID {
        val uuid = UUID.randomUUID()
        for (usedUUID in sessionMap.values) {
            if (uuid == usedUUID) {
                return getNewSession(player)
            }
        }
        sessionMap[player] = uuid
        return uuid
    }

    fun getSessions(): String {
        val data = arrayListOf<Any>()
        for (sessionData in sessionMap) {
            val session = arrayListOf<Any>()
            session.add(sessionData.key)
            session.add(sessionData.value)
            data.add(session)
        }
        return data.toString()
    }

}