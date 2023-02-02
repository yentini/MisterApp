package com.example.misterapp.domain.usecases.team

import com.example.misterapp.data.repository.TeamRepository
import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import javax.inject.Inject

class AddTeamUseCase @Inject constructor(
    private val teamRepository: TeamRepository
){
    suspend operator fun invoke(teamModel: TeamModel){
        teamRepository.add(teamModel)
    }
}