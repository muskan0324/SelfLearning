package com.example.selflearning.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.example.selflearning.activities.ui.theme.SelfLearningTheme
import com.example.selflearning.dao.ContactDAO
import com.example.selflearning.database.ContactDB
import com.example.selflearning.entity.Contact
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class RoomDatabaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyDB(this)

                }
            }
        }
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyDB(roomDatabaseActivity: RoomDatabaseActivity) {
    var scope= rememberCoroutineScope()
    var dbInstance=ContactDB.getInstance(LocalContext.current).contactDAO()


    Column() {
        Button(onClick = { getData(dbInstance, roomDatabaseActivity) }) {
            Text("GET Data")
        }
        Button(onClick = { insertData(dbInstance) }) {
            Text("Insert Data")
        }
    }

}

fun getData(dbInstance: ContactDAO, roomDatabaseActivity: RoomDatabaseActivity) {
    dbInstance.getContacts().observe(roomDatabaseActivity, Observer {
        Log.d("contact",it.toString())
    })
}
@SuppressLint("CoroutineCreationDuringComposition")
fun insertData(dbInstance: ContactDAO){
    GlobalScope.launch{
        dbInstance.insert(Contact(name="muskan",phone="9056700176", date = Date(), isActive = 0))
    }

}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SelfLearningTheme {
        Greeting3("Android")
    }
}