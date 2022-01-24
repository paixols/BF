package com.bf.bfmap.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "BF_PaymentMethod")
open class PaymentMethod {

    @ColumnInfo(name = "card_number")
    var cardNumber: String? = null

    @ColumnInfo(name = "exp_date")
    var expirationDate: String? = null

    @ColumnInfo(name = "processor")
    var processor: String? = null
}