package jiglionero.dogfoodtestapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jiglionero.dogfoodtestapp.ui.screen.breed.BreedImagesScreen
import jiglionero.dogfoodtestapp.ui.screen.main.MainScreen
import jiglionero.dogfoodtestapp.ui.theme.DogFoodTestAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DogFoodTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "breeds") {
                        composable("breeds") {
                            MainScreen(
                                onNavigateToBreedImages = { name, parentName ->
                                    var dest = "breeds/images/$name"
                                    if (parentName != null) {
                                        dest = "$dest?parentName=$parentName"
                                    }
                                    navController.navigate(dest)
                                }
                            )
                        }
                        composable("breeds/images/{name}?parentName={parentName}") {
                            BreedImagesScreen(
                                it.arguments?.getString("name") ?: "",
                                it.arguments?.getString("parentName"))
                        }
                    }
                }
            }
        }
    }
}