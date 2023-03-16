package com.example.misterapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.misterapp.core.Converters
import com.example.misterapp.data.repository.*

@Database(
    entities = [TemporadaEntity::class, TeamEntity::class, PlayerEntity::class, TeamPlayersEntity::class, MatchEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MisterAppDatabase: RoomDatabase() {
    abstract fun temporadaDao(): TemporadaDao
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
    abstract fun teamPlayersDao(): TeamPlayersDao
    abstract fun matchDao(): MatchDao
}