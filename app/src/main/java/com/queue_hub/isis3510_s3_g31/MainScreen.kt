package com.queue_hub.isis3510_s3_g31

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.room.Room
import com.queue_hub.isis3510_s3_g31.data.places.PlacesRepository
import com.queue_hub.isis3510_s3_g31.data.places.local.PlacesDatabase
import com.queue_hub.isis3510_s3_g31.navigation.AppNavigation
import com.queue_hub.isis3510_s3_g31.ui.components.BottomNavItem
import com.queue_hub.isis3510_s3_g31.ui.screens.detail.DetailScreen
import com.queue_hub.isis3510_s3_g31.ui.screens.detail.DetailViewModel
import com.queue_hub.isis3510_s3_g31.ui.screens.home.HomeScreen
import com.queue_hub.isis3510_s3_g31.ui.screens.recommended.RecommendedScreen
import com.queue_hub.isis3510_s3_g31.ui.screens.recommended.RecommendedViewModel

@Composable
fun MainScreen(navController: NavController) {

    val navItemList = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            label = "Queues",
            icon = Icons.Default.Face
        ),
        BottomNavItem(
            label = "Recommended",
            icon = Icons.Default.Favorite
        ),
        BottomNavItem(
            label = "Explore",
            icon = Icons.Default.Info
        ),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        label = {
                            Text(text = item.label) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController
            )

    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int, navController: NavController){
    val context = LocalContext.current
    val placesDb = Room.databaseBuilder(context = context, PlacesDatabase::class.java , name="places_db " ).build()
    val placesDAO = placesDb.dao
    val repository = PlacesRepository(placesDAO)
    when(selectedIndex){
        0 -> HomeScreen(navController = navController, modifier = Modifier)
        1 -> HomeScreen(navController = navController, modifier = Modifier)
        2 -> RecommendedScreen(navController = navController, recommendedViewModel = RecommendedViewModel(placesRepository = repository))
        3 -> DetailScreen(navController = navController , detailViewModel = DetailViewModel(placesRepository = repository) ,  modifier = Modifier)

    }
}