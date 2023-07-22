package com.example.selflearning.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.selflearning.activities.ui.theme.SelfLearningTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class CoroutineActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfLearningTheme {
//                MyApp()
                CoroutineScope(Dispatchers.IO).launch {
//                    coroutineBuildersAsyncAwait()
                    jobHierarchy()
                }
            }
        }
    }
}

// Explains the cooperation of different coroutines
fun MyApp(){
    CoroutineScope(Dispatchers.IO).launch {
        task1()
    }
    CoroutineScope(Dispatchers.IO).launch {
        task2()
    }

}

suspend fun task1() {
    Log.d("task1","start")
    yield()
    Log.d("task1","end")
}

suspend fun task2() {
    Log.d("task2","start")
    yield()
    Log.d("task2","end")
}


/**
 * explains the use of job.join() - keeps waiting until the coroutine is completed
 *
 */
suspend fun coroutineBuildersJoin(){
    var followers=0
    var job=CoroutineScope(Dispatchers.IO).launch {
       followers= getFollowers()
    }
    job.join()
    Log.d("followers:","$followers")
}

/**
 * explains the use of async await - keeps waiting until the we don't get the result
returns a deferred object which in future gives the result
 */
suspend fun coroutineBuildersAsyncAwait(){
    var followers=0
    var job=CoroutineScope(Dispatchers.IO).async{
       getFollowers()
       }
    Log.d("followers:",job.await().toString())
}

/**
 * explains how the hierarchy of parent child job works
 * parent's job is  not finished until the job of child is done
 */

suspend fun jobHierarchy(){

   var job= GlobalScope.launch {
        Log.d("parent","$coroutineContext")
        Log.d("parent","started")
       var childjob= launch {
            Log.d("child1","$coroutineContext")
            Log.d("child1","started")
            delay(4000)
            Log.d("child1","ended")
        }
        delay(3000)
       childjob.cancel()
        Log.d("parent","ended")
    }
//    job.join()
    job.cancel()
    Log.d("parent","completed")

}


suspend fun getFollowers():Int{
    delay(1000)
    return 54

}