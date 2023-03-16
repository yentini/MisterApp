package com.example.misterapp.domain.usecases.match

import com.example.misterapp.data.repository.MatchRepository
import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.MatchModel
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import javax.inject.Inject

class DeleteMatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository
){
    suspend operator fun invoke(matchModel: MatchModel){
        matchRepository.delete(matchModel)
    }
}