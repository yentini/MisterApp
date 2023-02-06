package com.example.misterapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_TABLE
import com.example.misterapp.data.MisterAppDatabase
import com.example.misterapp.data.repository.PlayerDao
import com.example.misterapp.data.repository.TeamDao
import com.example.misterapp.data.repository.TeamPlayersDao
import com.example.misterapp.data.repository.TemporadaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTeamPlayersDao(misterAppDatabase: MisterAppDatabase): TeamPlayersDao{
        return misterAppDatabase.teamPlayersDao()
    }

    @Provides
    fun provideTemporadaDao(misterAppDatabase: MisterAppDatabase): TemporadaDao{
        return misterAppDatabase.temporadaDao()
    }

    @Provides
    fun provideTeamDao(misterAppDatabase: MisterAppDatabase): TeamDao{
        return misterAppDatabase.teamDao()
    }

    @Provides
    fun providePlayerDao(misterAppDatabase: MisterAppDatabase): PlayerDao{
        return misterAppDatabase.playerDao()
    }

    @Provides
    fun provideMisterAppDatabase(@ApplicationContext appContext: Context): MisterAppDatabase{
        return Room.databaseBuilder(appContext, MisterAppDatabase::class.java, TEMPORADAS_TABLE).build()
    }
}