package com.example.selflearning

const val ARG1_KEY ="id"
const val ARG2_KEY="name"
sealed class NavGraphScreens(val route:String){

    object Home :NavGraphScreens("home")
    object Detail:NavGraphScreens(
        "detail/{$ARG1_KEY}/{$ARG2_KEY}"){
//        "detail?id={$ARG1_KEY}&name={$ARG2_KEY}"){    //optional argument
        fun passId(id:Int?=0):String{
            return "detail${id}"
//            return "detail?id=$id"
        }
        fun passName(name:String?):String{
            return "detail/$name"
//            return "detail?name=$name"
        }
        fun passIdAndName(id: Int?,name:String?=null):String{
            return "detail/$id/$name"
//            return "detail?id=$id"
        }
    }
}
