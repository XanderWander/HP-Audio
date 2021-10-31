package com.hp.audio.socket.packets

interface Packet {

    val id: Int

    fun getID(): Int {
        return this.id
    }

    fun data(): ArrayList<Any> {
        return arrayListOf()
    }

}