package com.example.todoskotlin.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.todoskotlin.databinding.ActivityMainBinding
import com.example.todoskotlin.utils.TodoRetrofitService
import com.example.todoskotlin.viewmodel.TodoAdapter
import retrofit2.HttpException

const val TAG = "mainactivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpRecyclerViewAdapter()
        initializeNetworkRequest()
    }

    private fun setUpRecyclerViewAdapter() = binding.recyclerView.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
//        layoutManager = LinearLayoutManager(this@MainActivity)
    }

//    Coroutines assincrona
    private fun initializeNetworkRequest() {
        lifecycleScope.launchWhenCreated {
            binding.loadingBar.isVisible = true

            val apiResponse = try {
                TodoRetrofitService.api.getTodos()

            } catch (e: HttpException) {
                binding.loadingBar.isVisible = false
                Log.e(TAG, "error no servidor")

                return@launchWhenCreated
            }

            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                todoAdapter.todos = apiResponse.body()!!
            } else {
                Log.e(TAG, "erro no recebimento de dados, verificar chamada get")
            }
            binding.loadingBar.isVisible = false
        }
    }
}

