package com.example.selflearning.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.selflearning.activities.ui.theme.SelfLearningTheme
//import com.example.selflearning.dagger.EmailService
//import com.example.selflearning.dagger.NotificationService
//import com.example.selflearning.dagger.UserRegistrationComponent
//import com.example.selflearning.dagger.UserRegistrationService
import javax.inject.Inject

class DaggerActivity : ComponentActivity() {
//    @Inject
//    lateinit var userRegistrationService: UserRegistrationService  //field injection
//
//    @Inject
//    lateinit var emailService: EmailService  //field injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    without dagger (DEPENDENCY INJECTION)
                     */
//                    val userRepository=UserRepository()
//                    val emailService=EmailService()
//                    val userRegistrationService=UserRegistrationService(userRepository,emailService)

//                    val component=DaggerUserRegistrationComponent.builder().build()
////                    constructorInjection(component)
//                    component.inject(this)  //field injection
//                    fieldInjection(userRegistrationService,emailService)

                }
            }
        }
    }
}

/*
     WITH Dagger2
    "DaggerUserRegistrationComponent" is automatically created by dagger corresponding to the "UserRegistrationComponent"
*/

/*
Constructor Injection
 */
//fun constructorInjection(component: UserRegistrationComponent) {
//
//    val userRegistrationService=component.getUserRegistrationService()
//    val emailService=component.getEmailService()
//    userRegistrationService.registerUser("xyz","***")
//}
///*
//Field Injection
// */
//fun fieldInjection(
//    userRegistrationService: UserRegistrationService,
//    emailService: EmailService,
//) {
//    userRegistrationService.registerUser("xyz","***")
//
//}
