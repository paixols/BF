package com.bf.bfmap.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bf.bfmap.domain.entities.BoostLocation

@Dao
interface BoostLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoostLocation(boostLocation: BoostLocation)

}