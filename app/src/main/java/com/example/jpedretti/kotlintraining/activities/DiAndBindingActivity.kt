package com.example.jpedretti.kotlintraining.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jpedretti.kotlintraining.BindingConverters
import com.example.jpedretti.kotlintraining.viewModels.DIViewModel
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.databinding.ActivityDiAndBindingBinding
import org.koin.android.architecture.ext.viewModel

class DiAndBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiAndBindingBinding
    private val viewModel by viewModel<DIViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_di_and_binding)
        binding.viewModel = viewModel
        viewModel.onCreate()
    }
}
