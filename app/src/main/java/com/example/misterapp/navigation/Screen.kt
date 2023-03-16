package com.example.misterapp.navigation

import com.example.misterapp.core.Constants.Companion.MATCHES_SCREEN
import com.example.misterapp.core.Constants.Companion.MODIFY_TEMPORADA_SCREEN
import com.example.misterapp.core.Constants.Companion.MY_TEAMS_SCREEN
import com.example.misterapp.core.Constants.Companion.PLAYERS_SCREEN
import com.example.misterapp.core.Constants.Companion.PLAYER_SCREEN
import com.example.misterapp.core.Constants.Companion.TEAM_PLAYERS_SCREEN
import com.example.misterapp.core.Constants.Companion.TEAM_SCREEN
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_SCREEN

sealed class Screen(val route: String){
    object TemporadasScreen: Screen(TEMPORADAS_SCREEN)
    object ModifyTemporadaScreen: Screen(MODIFY_TEMPORADA_SCREEN)
    object MyTeamsScreen: Screen(MY_TEAMS_SCREEN)
    object TeamScreen: Screen(TEAM_SCREEN)
    object PlayersScreen: Screen(PLAYERS_SCREEN)
    object PlayerScreen: Screen(PLAYER_SCREEN)
    object TeamPlayersScreen: Screen(TEAM_PLAYERS_SCREEN)
    object MatchesScreen: Screen(MATCHES_SCREEN)
}
