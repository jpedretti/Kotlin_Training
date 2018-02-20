package com.example.jpedretti.kotlintraining.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.controllers.MyController
import com.example.jpedretti.kotlintraining.injection.CoroutineContextInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {

    //private var greeting: TextView? = null
    private var MyController: MyController = MyController("Go to Dagoba")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseControllerResult.text = MyController.doSomeBaseControllerStuff()
        val otherModel = MyController.createOtherModel()
//        greeting = (findViewById<TextView>(R.id.greeting)).apply{
//            text = "MyController"
//        }
//        greeting?.text = greetings("1", "2")

        async(CoroutineContextInjector.uiContext) {
            greeting.text = greetings(otherModel.name, otherModel.age.toString())
            MyController.lambdaWithListsAsync().await()

            for ((index, i) in (1..10).withIndex()) {
                Log.d("MyController", "index: $index; valor: $i")
            }

            for (i in (1..10)) {
                Log.d("MyController", "index: sem index; valor: $i")
            }
        }
    }

    fun clickGoToDI(view: View) {
        startActivity(Intent(this, DiAndBindingActivity::class.java))
    }

    private fun greetings(firstPhrase: String, secondPhrase: String) = when (firstPhrase) {
        "MyController" -> "$secondPhrase $firstPhrase"
        "yoda" -> "when $secondPhrase years old you reach look as good you will not"
        else -> "$firstPhrase & $secondPhrase"
    }

//    private fun greetings(firstPhrase: String, secondPhrase: String): String {
//        return when(firstPhrase){
//            "MyController" -> "$secondPhrase $firstPhrase"
//            else -> "$firstPhrase & $secondPhrase"
//        }
//    }
}
