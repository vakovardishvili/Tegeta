package com.example.myapplication.color.presentation.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.color.presentation.list.model.Result
import com.example.myapplication.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColorDetailsActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: ColorDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        viewModel.fetchData(intent.getLongExtra("id", 0))

        observeData()
    }

    private fun observeData() {
        viewModel.colorsLiveData.observe(this) { result ->
            if (result is Result.Success) {
                val color = result.data
                binding.userName.text = color.userName
                binding.createDate.text = color.dateCreated
                Glide.with(baseContext)
                    .load(color.imageUrl)
                    .centerCrop()
                    .into(binding.image)

                binding.image.setOnClickListener {
                    openUrl(color.imageUrl)
                }

                color.rgb?.let {
                    binding.root.setBackgroundColor(
                        Color.rgb(
                            it.red,
                            it.green,
                            it.blue,
                        )
                    )
                }
            }

        }
    }

    private fun openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    companion object {

        fun start(activity: Activity, colorId: Long) {
            Intent(activity, ColorDetailsActivity::class.java).apply {
                putExtra("id", colorId)
            }.also {
                activity.startActivity(it)
            }
        }
    }

    /*    inline fun <reified VM : ViewModel> ComponentActivity.viewModel(
            factory: ColorDetailsViewModel.Factory,
            colorId: Long
        ): Lazy<VM> = lazy {
            ViewModelProvider(this,factory.create(colorId))
                .get(VM::class.java)
        }*/

}

