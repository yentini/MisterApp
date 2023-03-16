package com.example.misterapp.domain.model

import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.core.Constants.Companion.NO_VALUE_INT

data class TeamModel (
    val teamId: Int = System.currentTimeMillis().hashCode(),
    val temporadaId: Int = NO_VALUE_INT,
    var category: String = NO_VALUE,
    val level: String = NO_VALUE,
    val year: Int = NO_VALUE_INT,
    val club: String = NO_VALUE
)