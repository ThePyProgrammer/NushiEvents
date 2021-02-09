package com.thepyprogrammer.nushievents.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Database internal constructor(jsonStream: InputStream) : ArrayList<Event>() {
    companion object {
        var currentOccurence: Database? = null
        var currentItem: Event? = null
    }

    val itemMap = HashMap<String, Event>()

    init {
        val sc = Scanner(jsonStream)
        val s = StringBuilder()
        while(sc.hasNext()) s.append(sc.nextLine())
        sc.close()
        val json = s.toString()
        val gson = Gson()
        val sType = object : TypeToken<List<GsonEvent>>() { }.type
        gson.fromJson<List<GsonEvent>>(json, sType)?.forEach {
            val ev = it.toEvent()
            add(ev)
            itemMap[ev.title] = ev
        }
    }

}