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
       temporadaDao.getTemporadas().map { items -> items.map { TemporadaModel(it.id, it.name, it.favorite) } }

    suspend fun add(temporada: TemporadaModel){
        temporadaDao.addTemporada(temporada.toData())
    }

    suspend fun delete(temporada: TemporadaModel){
        temporadaDao.deleteTemporada(temporada.toData())
    }

    suspend fun update(temporada: TemporadaModel){
        temporadaDao.updateTemporada(temporada.toData())
    }

    suspend fun updateTemporadas(temporadas: List<TemporadaModel>){
        temporadaDao.updateTemporadas(temporadas.map { it.toData()})
    }

    fun get(id: Int): TemporadaModel{
        var temporadaEntity = temporadaDao.getTemporada(id)
        return TemporadaModel(temporadaEntity.id, temporadaEntity.name, temporadaEntity.favorite)
    }

    fun getNumTemporadas(): Int{
        return temporadaDao.getNumTemporadas()
    }
}

fun TemporadaModel.toData(): TemporadaEntity{
    return TemporadaEntity(this.id, this.name, this.favorite)
}