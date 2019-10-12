package com.example.jpedretti.kotlintraining.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.databinding.ActivityDiAndBindingBinding
import com.example.jpedretti.kotlintraining.viewModel.DIViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DiAndBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiAndBindingBinding
    private val viewModel by viewModel<DIViewModel>()
    private val planetAdapter = PlanetAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_di_and_binding)
        binding.viewModel = viewModel
        binding.planetsRecyclerView.adapter = planetAdapter
        viewModel.onCreate()
        viewModel.model.planets.observe(this, Observer {
            planetAdapter.setPlanets(it)
        })
    }
}
