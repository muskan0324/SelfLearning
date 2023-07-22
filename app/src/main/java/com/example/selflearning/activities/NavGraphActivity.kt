package com.example.selflearning.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.selflearning.ARG1_KEY
import com.example.selflearning.ARG2_KEY
import com.example.selflearning.NavGraphScreens
import com.example.selflearning.ui.theme.SelfLearningTheme

class NavGraphActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfLearningTheme {
                navController= rememberNavController()
                NavHost(navController = navController,
                    startDestination =NavGraphScreens.Detail.route
//                startDestination = "detail/{1}/{muskan}"   //this doesn't work why????
                )
                {
                    composable(NavGraphScreens.Home.route){
                        HomeScreen(navController)
                    }
                    composable(
                        NavGraphScreens.Detail.route
                    ,arguments= listOf(
                            navArgument(name= ARG1_KEY){
                                type= NavType.IntType
                                defaultValue=1
                            },
                            navArgument(ARG2_KEY){
                                type= NavType.StringType
//                                defaultValue="muskan"
                                nullable=false
                            }
                    )
                    ) {
                        var id=it.arguments?.getInt(ARG1_KEY)
                        var name=it.arguments?.getString(ARG2_KEY)
                        Log.d("arguments","${id} ")
                        Log.d("arguments","${name==null} ")
                        DetailScreen(navController)
                    }
                }


            }
        }
    }
}
@Composable
fun HomeScreen(navController: NavHostController) {
    var v:String?=null
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier=Modifier.clickable
                {navController.navigate("detail/1/$v")},
//                { navController.navigate(NavGraphScreens.Detail.passIdAndName(1,"muskan") ) },
                text="Home",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color=Color.Magenta
            )

        }
    }
}

@Composable
fun DetailScreen(navController: NavHostController) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier=Modifier.clickable
                { navController.navigate(NavGraphScreens.Home.route){
                    popUpTo(NavGraphScreens.Home.route){
                        inclusive=true
                    }
                } },
                text="Detail",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color=Color.Magenta
            )

        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SelfLearningTheme {
        Greeting2("Android")
    }
}