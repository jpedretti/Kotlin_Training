package com.example.jpedretti.kotlintraining

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {

    //private var greeting: TextView? = null
    private var myController: MyController = MyController("this")

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myController.doSomeBaseControllerStuff()
        val myModel = myController.createMyModel()

        async(UI) {
//        greeting = (findViewById<TextView>(R.id.greeting)).apply{
//            text = "banana"
//        }
//        greeting?.text = greetings("1", "2")

            greeting.text = greetings(myModel.name.get()!!, myModel.age.get()!!.toString())
            myController.lambdaWithListsAsync().await()

            for ((index, i) in (1..10).withIndex()) {
                Log.d("banana", "index: $index; valor: $i")
            }
            for (i in (1..10)) {
                Log.d("banana", "index: sem index; valor: $i")
            }
        }
    }

    fun click(view: View) {
        startActivity(Intent(this, BindingActivity::class.java))
    }

    private fun greetings(firstPhrase: String, secondPhrase: String): String = when(firstPhrase){
        "banana" -> "$secondPhrase $firstPhrase"
        else -> "$firstPhrase & $secondPhrase"
    }

//    private fun greetings(firstPhrase: String, secondPhrase: String): String {
//        return when(firstPhrase){
//            "banana" -> "$secondPhrase $firstPhrase"
//            else -> "$firstPhrase & $secondPhrase"
//        }
//    }
}
