package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample.ui.theme.SampleTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object Main
@Serializable
object Second

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val controller = rememberNavController()
                    NavHost(navController = controller, startDestination = Main) {
                        composable<Main> {
                            MainScreen(controller)
                        }
                        composable<Second> {
                            SecondScreen(controller)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun MainScreen(
    controller: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.clickable {}, text = "MainScreen")
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { controller.navigate(Second) }) {
            Text(text = "go Main")
        }
    }
}

@Composable
fun SecondScreen(controller: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = Unit) {
            //navigate the screen and go back before the animation ends, the screen freezes.
            delay(200)
            controller.popBackStack()
        }
        Text(modifier = Modifier.clickable {}, text = "SecondScreen")
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { controller.popBackStack() }) {
            Text(text = "go back")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTheme {
        Greeting("Android")
    }
}