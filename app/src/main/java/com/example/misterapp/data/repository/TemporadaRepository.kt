package com.example.misterapp.data.repository

import com.example.misterapp.data.TemporadaEntity
import com.example.misterapp.domain.model.TemporadaModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemporadaRepository @Inject constructor(private val temporadaDao: TemporadaDao) {

    val temporadas: Flow<List<TemporadaModel>> =
       temporadaDao.getTemporadas().map { items -> items.map { TemporadaModel(it.id, it.name) } }

    /* val temporadas: Flow<List<TemporadaWithTeamsModel>> =
    temporadaDao.getTemporadasWithTeams().map { items -> items.map {
        TemporadaWithTeamsModel(
            it.temporada,
            it.teams!!.map {
             TeamModel(
                 it.teamId,
                 it.name,
                 it.temporadaId
             )
            }
        )
    } }
    */

    suspend fun add(temporada: TemporadaModel){
        temporadaDao.addTemporada(temporada.toData())
    }

    suspend fun delete(temporada: TemporadaModel){
        temporadaDao.deleteTemporada(temporada.toData())
    }

    suspend fun update(temporada: TemporadaModel){
        temporadaDao.updateTemporada(temporada.toData())
    }

    suspend fun get(id: Int): TemporadaModel{
        var temporadaEntity = temporadaDao.getTemporada(id)
        return TemporadaModel(temporadaEntity.id, temporadaEntity.name)
    }

}

fun TemporadaModel.toData(): TemporadaEntity{
    return TemporadaEntity(this.id, this.name)
}