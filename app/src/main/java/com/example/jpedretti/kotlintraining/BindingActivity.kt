package com.example.jpedretti.kotlintraining

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jpedretti.kotlintraining.databinding.ActivityBindingBinding
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class BindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBindingBinding
    private var myModel: MyModel =
            MyModel(ObservableField(null), ObservableField(null)).apply {
        loading.set(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_binding)
        binding.model = myModel
        binding.activity = this@BindingActivity

        //async (CommonPool) {
        async {
            delay(2000)
            myModel.name.set("banana")
            myModel.age.set(33)
            myModel.loading.set(false)
        }
    }

    fun loadOther() {
        myModel.loading.set(true)
        launch {
            val result = getData()
            Log.d("getData", result)
            val done = lotsOfDots()
            Log.d("lotsOfDots", done.isCompleted.toString())
        }

        Log.d("loadOther", "FINISHED")
    }

    private suspend fun getData(): String {
        delay(2_000)
        myModel.name.set("fdghdfghdfgh")
        myModel.age.set(19)
        myModel.loading.set(false)
        return "DONE"
    }

    private fun lotsOfDots() = async {
        val jobs = List(100) { // launch a lot of coroutines and list their jobs
            launch {
                delay(5000)
                Log.d("dots",".")
            }
        }

        jobs.forEach { it.join() } // wait for all jobs to complete
        "DONE"
    }
}
