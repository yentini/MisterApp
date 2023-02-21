package com.example.misterapp.ui.temporada.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.misterapp.R
import com.example.misterapp.core.Constants.Companion.MY_TEAMS_SCREEN
import com.example.misterapp.core.Constants.Companion.PLAYERS_SCREEN
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_SCREEN
import com.example.misterapp.navigation.Screen
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun BottomBar(
    temporadasViewModel: TemporadasViewModel = hiltViewModel(),
    navController: NavController
) {
    BottomNavigation(elevation = 10.dp) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.player),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "")
            },
            label = { Text(text = PLAYERS_SCREEN) },
            selected = currentRoute == Screen.PlayersScreen.route,
            onClick = {
                if(currentRoute != Screen.PlayersScreen.route){
                    /*navController.graph?.startDestinationRoute?.let {
                        navController.popBackStack(it, true)
                    }*/
                    navController.navigate(Screen.PlayersScreen.route){
                        launchSingleTop = true
                    }
                }
            })

        BottomNavigationItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.temporadas),
                modifier = Modifier.size(24.dp),
                contentDescription = ""
            )
        },
            label = { Text(text = TEMPORADAS_SCREEN) },
            selected = currentRoute == Screen.TemporadasScreen.route,
            onClick = {
                if(currentRoute != Screen.TemporadasScreen.route){
                /*    navController.graph?.startDestinationRoute?.let {
                        navController.popBackStack(it, true)
                    }*/
                    navController.navigate(Screen.TemporadasScreen.route){
                        launchSingleTop = true
                    }
                }
            })

        BottomNavigationItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.misequipos),
                modifier = Modifier.size(24.dp),
                contentDescription = ""
            )
        },
            label = { Text(text = MY_TEAMS_SCREEN) },
            selected = currentRoute?.contains(Screen.MyTeamsScreen.route) ?: false,
            onClick = {
                if(!currentRoute!!.contains(Screen.MyTeamsScreen.route) && temporadasViewModel.temporadaFavorite.value.id != -1){
                    /*    navController.graph?.startDestinationRoute?.let {
                            navController.popBackStack(it, true)
                        }*/
                    navController.navigate(
                        "${Screen.MyTeamsScreen.route}/${temporadasViewModel.temporadaFavorite.value.id}"
                    ){
                        launchSingleTop = true
                    }
                }
            })
    }
}