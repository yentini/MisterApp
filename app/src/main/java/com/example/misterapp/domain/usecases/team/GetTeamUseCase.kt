package com.example.misterapp.domain.usecases.team

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TeamRepository
import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(
    private val teamRepository: TeamRepository
){
    suspend operator fun invoke(id: Int): TeamModel {
        return teamRepository.get(id)
    }
}