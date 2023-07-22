package com.example.selflearning.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.selflearning.activities.ui.theme.SelfLearningTheme

class IntentsAndIntentFiltersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfLearningTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = {
                        //-----EXPLICIT INTENT----//
                        startActivity(
                            Intent(
                                applicationContext,
                                ContentProviderActivity::class.java
                            )
                        )
                        //-----EXPLICIT INTENT----//
                        //suppose we want to launch youtube in our application.that is called explicit intent
//                        try {
//                            //Intent.ACTION_MAIN means open the main application with the given package
//                            var openYouTubeIntent = Intent(Intent.ACTION_MAIN).also {
//                                it.`package` = "com.google.android.youtube"
//                            }
//                            if (openYouTubeIntent.resolveActivity(packageManager) != null) {
//                                startActivity(openYouTubeIntent)
//                            }
//                        } catch (ex: Exception) {
//                            throw Exception(ex.message)
//                        }

                        //------------------IMPLICIT INTENT----//

                        var sendEmailIntent=Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("test@gmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                            putExtra(Intent.EXTRA_TEXT, "This is the content of my email")

                        }
                            try {
                                if (sendEmailIntent.resolveActivity(packageManager) != null) {
                                    startActivity(sendEmailIntent)
                                }
                            }
                            catch (ex:Exception){
                                throw Exception(ex.message)
                        }
                    }){
                        Text("Click me")
                    }

                }

            }
        }
    }
}

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    SelfLearningTheme {
        Greeting5("Android")
    }
}