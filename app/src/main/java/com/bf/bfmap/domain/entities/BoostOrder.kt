package com.bf.bfmap.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BF_Order")
open class BoostOrder(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 1
) {

    @ColumnInfo(name = "delivery_time")
    var deliveryTime: Int? = null

    @Embedded
    var paymentMethod: PaymentMethod? = null
}