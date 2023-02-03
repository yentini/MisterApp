package com.example.misterapp.domain.usecases.team

import com.example.misterapp.data.repository.TeamRepository
import com.example.misterapp.domain.model.TeamModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(
    private val teamRepository: TeamRepository
){
    operator fun invoke(id: Int): Flow<TeamModel> {
        return teamRepository.get(id)
    }
}