package com.bf.bfmap.di

import android.content.Context
import androidx.room.Room
import com.bf.bfmap.db.BFDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(context: Context): BFDatabase =
        Room.databaseBuilder(
            context,
            BFDatabase::class.java,
            "BF_Map_Database"
        ).build()
}
