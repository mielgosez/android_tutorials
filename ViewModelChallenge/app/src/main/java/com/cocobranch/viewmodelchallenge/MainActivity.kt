package com.cocobranch.viewmodelchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cocobranch.viewmodelchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModelFactory = MainActivityViewModelFactory(125)
        setContentView(binding.root)
        //viewModel = MainActivityViewModel()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        binding.apply {
            viewModel.countData.observe(this@MainActivity, Observer {
                tvAdd.text = it.toString()
            })
            btn.setOnClickListener {
                viewModel.addValue(etNumber.text.toString().toInt())
            }
        }
    }
}