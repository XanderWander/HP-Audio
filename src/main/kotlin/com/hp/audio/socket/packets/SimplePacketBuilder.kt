package com.hp.audio.socket.packets

class SimplePacketBuilder {

    companion object {

        fun buildPacketData(id: Int, domain: Int, type: Int, data: ArrayList<ArrayList<Any>>): ArrayList<Any> {
            val packet = arrayListOf<Any>()
            packet.add(id)
            packet.add(domain)
            packet.add(type)
            packet.add(data)
            return packet
        }

        fun buildPacketData(id: Int, domain: Int, type: Int, vararg data: Any): ArrayList<Any> {
            val packet = arrayListOf<Any>()
            val parentList = arrayListOf<Any>()
            val childList = arrayListOf<Any>()
            for (value in data) {
                childList.add(value)
            }
            parentList.add(childList)
            packet.add(id)
            packet.add(domain)
            packet.add(type)
            packet.add(parentList)
            return packet
        }

        fun buildPacketData(vararg data: Any): ArrayList<Any> {
            val parentList = arrayListOf<Any>()
            val childList = arrayListOf<Any>()
            for (value in data) {
                childList.add(value)
            }
            parentList.add(childList)
            return parentList
        }

    }

}