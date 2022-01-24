package com.bf.bfmap.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bf.bfmap.domain.entities.BoostOrder

@Dao
interface BoostOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoostOrder(boostOrder: BoostOrder)

    @Query("SELECT * FROM BF_Order where id = 1")
    suspend fun getOrder(): BoostOrder

    @Query("DELETE FROM BF_Order")
    suspend fun nukeTable()
}