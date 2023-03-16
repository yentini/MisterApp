package com.example.misterapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.misterapp.core.Constants.Companion.PLAYER_ID
import com.example.misterapp.core.Constants.Companion.TEAM_ID
import com.example.misterapp.core.Constants.Companion.TEMPORADA_ID
import com.example.misterapp.navigation.Screen.*
import com.example.misterapp.ui.my_teams.MyTeamsScreen
import com.example.misterapp.ui.players.PlayerScreen
import com.example.misterapp.ui.players.PlayersScreen
import com.example.misterapp.ui.team.TeamPlayersScreen
import com.example.misterapp.ui.team.TeamScreen
import com.example.misterapp.ui.temporada.ModifyTemporadaScreen
import com.example.misterapp.ui.temporada.TemporadasScreen
import com.example.misterapp.ui.matches.MatchesScreen

@Composable
fun NavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = TemporadasScreen.route
    ){
        composable(
            route = TemporadasScreen.route
        ){
            TemporadasScreen(
                navController = navController,
                navigateToMyTeamsScreen = {
                    temporadaId ->
                        navController.navigate(
                            "${MyTeamsScreen.route}/${temporadaId}"
                        )
                }
            )
        }
        composable(
            route = "${ModifyTemporadaScreen.route}/{$TEMPORADA_ID}",
            arguments = listOf(
                navArgument(TEMPORADA_ID){
                    type = NavType.IntType
                }
            )
        ){
            navBackStackEntry ->
                val temporadaId = navBackStackEntry.arguments?.getInt(TEMPORADA_ID) ?: 0
            ModifyTemporadaScreen(
                temporadaId = temporadaId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = PlayersScreen.route
        ){
            PlayersScreen(
                navController = navController,
                navigateToPlayerScreen = {
                        playerId ->
                            navController.navigate(
                        "${PlayerScreen.route}/${playerId}"
                    )
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${PlayerScreen.route}/{$PLAYER_ID}",
            arguments = listOf(
                navArgument(PLAYER_ID){
                    type = NavType.IntType
                }
            )
        ){
            navBackStackEntry ->
                val playerId = navBackStackEntry.arguments?.getInt(PLAYER_ID) ?: 0
            PlayerScreen(
                playerId = playerId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${MyTeamsScreen.route}/{$TEMPORADA_ID}",
            arguments = listOf(
                navArgument(TEMPORADA_ID){
                    type = NavType.IntType
                }
            )
        ){
            navBackStackEntry ->
                val temporadaId = navBackStackEntry.arguments?.getInt(TEMPORADA_ID) ?: 0
            MyTeamsScreen(
                navController = navController,
                temporadaId = temporadaId,
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToMyTeamScreen = {
                        teamId -> navController.navigate(
                                "${TeamScreen.route}/${teamId}"
                                )
                },
                navigateModifyTemporada = {
                            navController.navigate(
                                "${ModifyTemporadaScreen.route}/${temporadaId}"
                            )
                }
            )
        }
        composable(
            route = "${TeamScreen.route}/{$TEAM_ID}",
            arguments = listOf(
                navArgument(TEAM_ID){
                    type = NavType.IntType
                }
            )
        ){
            navBackStackEntry ->
                val teamId = navBackStackEntry.arguments?.getInt(TEAM_ID) ?: 0
                TeamScreen(
                    teamId = teamId,
                    navigateBack = {
                        navController.popBackStack()
                    },
                    navigateToTeamPlayersScreen = {
                            teamId -> navController.navigate(
                        "${TeamPlayersScreen.route}/${teamId}"
                            )
                    },
                    navigateToMatchesScreen = {
                            teamId -> navController.navigate(
                        "${MatchesScreen.route}/${teamId}"
                    )
                    }
                )
        }
        composable(
            route = "${TeamPlayersScreen.route}/{$TEAM_ID}",
            arguments = listOf(
                navArgument(TEAM_ID){
                    type = NavType.IntType
                }
            )
        ){
            navBackStackEntry ->
                val teamId = navBackStackEntry.arguments?.getInt(TEAM_ID) ?: 0
            TeamPlayersScreen(
                teamId = teamId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${MatchesScreen.route}/{$TEAM_ID}",
            arguments = listOf(
                navArgument(TEAM_ID){
                    type = NavType.IntType
                }
            )
        ){
            navBackStackEntry ->
                val teamId = navBackStackEntry.arguments?.getInt(TEAM_ID) ?: 0
            MatchesScreen(
                teamId = teamId,
                navController = navController,
                navigateBack = {
                    navController.popBackStack()
                }/*,
                navigateToMyTeamScreen = {
                        teamId -> navController.navigate(
                    "${TeamScreen.route}/${teamId}"
                        )
                },
                navigateModifyTemporada = {
                    navController.navigate(
                        "${ModifyTemporadaScreen.route}/${temporadaId}"
                    )
                }*/
            )
        }
    }
}