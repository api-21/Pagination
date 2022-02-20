package com.xpresscure.pagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.xpresscure.pagination.Adapter.DogsAdapter
import com.xpresscure.pagination.Retrofit.DogsViewModel
import com.xpresscure.pagination.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val dogsVM: DogsViewModel by viewModels()

    @Inject
    lateinit var adapter: DogsAdapter

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        getDogsList()

    }

    private fun getDogsList() {
        lifecycleScope.launchWhenStarted {
            dogsVM.GetAllDogs().collectLatest { response ->

                Log.e("DogResponse", "$response")
                binding.mainRCV.layoutManager = GridLayoutManager(this@MainActivity, 2)
                binding.mainRCV.adapter = adapter
                adapter.notifyDataSetChanged()
                adapter.submitData(response)

            }
        }

    }

}