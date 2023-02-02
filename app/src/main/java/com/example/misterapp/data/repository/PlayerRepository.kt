package com.example.misterapp.data.repository

import com.example.misterapp.data.PlayerEntity
import com.example.misterapp.data.TemporadaEntity
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor(private val playerDao: PlayerDao) {

    val players: Flow<List<PlayerModel>> =
        playerDao.getAll().map { items -> items.map { PlayerModel(it.playerId,
                                                                it.name,
                                                                it.email,
                                                                it.phone,
                                                                it.birthday
                                                                )}}

    suspend fun add(player: PlayerModel){
        playerDao.addPlayer(player.toData())
    }

    suspend fun delete(player: PlayerModel){
        playerDao.deletePlayer(player.toData())
    }

    suspend fun update(player: PlayerModel){
        playerDao.updatePlayer(player.toData())
    }

    fun get(id: Int): Flow<PlayerModel>{
        var playerEntity: Flow<PlayerModel> = playerDao.getPlayer(id).map {
                                                    PlayerModel(it.playerId,
                                                                it.name,
                                                                it.email,
                                                                it.phone,
                                                                it.birthday
                                                    )
                                                }
        return playerEntity
    }

}

fun PlayerModel.toData(): PlayerEntity{
    return PlayerEntity(this.playerId, this.name, this.email, this.phone, this.birthday)
}