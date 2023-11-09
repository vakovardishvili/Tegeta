package com.example.myapplication.color.presentation.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.myapplication.color.presentation.detail.ColorDetailsActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.color.presentation.list.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColorsListActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ColorsListViewModel by viewModels()
    private val adapter = ColorListAdapter {
        ColorDetailsActivity.start(this, it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        binding.recyclerView.adapter = adapter

        observeData()
    }

    private fun observeData() {
        viewModel.colorsLiveData.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    adapter.submitList(result.data)
                }

                is Result.Loading -> {

                }

                is Result.Error -> {

                }
            }
        }
    }
}
