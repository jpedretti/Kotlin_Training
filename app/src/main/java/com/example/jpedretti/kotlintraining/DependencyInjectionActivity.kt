package com.example.jpedretti.kotlintraining

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_dependency_injection.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.android.architecture.ext.viewModel

class DependencyInjectionActivity : AppCompatActivity() {

    private val diViewModel: DIViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dependency_injection)
        app_name.text = resources.getText(R.string.app_name)
    }

    fun doServiceStuffByViewModelClick(view: View) {
        launch (UI){
            view_model_result.text = diViewModel.callDoTestServiceStuff().await()
        }
    }
}
