package com.example.navigationcomponent.realm.model

import androidx.room.PrimaryKey

import java.util.*

open class VisitInfo : RealmObject() {

    @PrimaryKey
    var id = UUID.randomUUID().toString()

    var visitCount: Int = 0

}

fun VisitInfo.updateCount(): VisitInfo {
    return apply {
        visitCount++
    }
}