package com.showti.temphilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.showti.core.utils.NetworkResult
import com.showti.temphilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.imgReplay.setOnClickListener {
           fetchResponse()
        }

        fetchData()
    }

    private fun fetchData(){
        fetchResponse()
        mainViewModel.response.observe(this) { it ->
            when (it) {
                is NetworkResult.Success -> {
                    binding.loading.visibility = View.GONE
                    Log.d(TAG,"Success")
                    it.data.let {
                        Log.d(TAG,"data :${it?.message}")
                        Glide.with(binding.root)
                            .load(it?.message)
                            .into(binding.dogImage)
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this,"Error :${it.message}",Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Error :${it.message}")
                }

                is NetworkResult.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                    Log.d(TAG, "Loading")
                }
            }
        }
    }

    private fun fetchResponse(){
        binding.loading.visibility = View.VISIBLE
        mainViewModel.fetchDogResponse()
    }
}