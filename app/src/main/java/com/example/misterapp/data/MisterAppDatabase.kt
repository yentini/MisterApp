package com.example.misterapp.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.misterapp.core.Converters
import com.example.misterapp.data.repository.PlayerDao
import com.example.misterapp.data.repository.TeamDao
import com.example.misterapp.data.repository.TemporadaDao

@Database(
    entities = [TemporadaEntity::class, TeamEntity::class, PlayerEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MisterAppDatabase: RoomDatabase() {
    abstract fun temporadaDao(): TemporadaDao
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
}