package com.bf.bfmap.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bf.bfmap.domain.entities.BoostLocation
import com.bf.bfmap.domain.entities.BoostOrder

@Database(
    entities = [
        BoostLocation::class,
        BoostOrder::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BFDatabase : RoomDatabase() {
    abstract fun boostLocationDao(): BoostLocationDao
    abstract fun boostOrderDao(): BoostOrderDao
}