package com.example.misterapp.domain.usecases.team

import com.example.misterapp.data.repository.TeamRepository
import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(private val teamRepository: TeamRepository) {
    operator fun invoke(): Flow<List<TeamModel>> = teamRepository.getTeams()
}