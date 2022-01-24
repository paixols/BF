package com.bf.bfmap.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "BF_Location"
)
open  class BoostLocation(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 1
) {

    @ColumnInfo(name = "lat")
    var lat: Double? = null

    @ColumnInfo(name = "lng")
    var lng: Double? = null
}
