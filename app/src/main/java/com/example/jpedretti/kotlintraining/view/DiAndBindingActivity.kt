package com.example.jpedretti.kotlintraining.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.databinding.ActivityDiAndBindingBinding
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.viewModel.DIViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.koin.android.architecture.ext.viewModel

class DiAndBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiAndBindingBinding
    private val viewModel by viewModel<DIViewModel>()
    private val itemBinding: ItemBinding<PlanetResult> = ItemBinding.of(BR.planet, R.layout.planet_item_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_di_and_binding)
        binding.viewModel = viewModel
        binding.itemBinding = itemBinding
        viewModel.onCreate()
    }
}
