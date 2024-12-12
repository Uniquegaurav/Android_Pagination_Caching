package com.example.manpaginationroomrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.manpaginationroomrecycler.data.db.ResultDatabase
import com.example.manpaginationroomrecycler.data.network.ApiClient
import com.example.manpaginationroomrecycler.data.network.MainAPI
import com.example.manpaginationroomrecycler.databinding.ActivityMainBinding
import com.example.manpaginationroomrecycler.domain.repository.MainDataRepository
import com.example.manpaginationroomrecycler.presentation.adapter.ItemRecyclerViewAdapter
import com.example.manpaginationroomrecycler.presentation.viewmodel.MainViewModel
import com.example.manpaginationroomrecycler.presentation.viewmodel.MainViewModelProviderFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var itemRecyclerViewAdapter: ItemRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this, MainViewModelProviderFactory(
                MainDataRepository(
                    mainAPI = ApiClient.mainApi,
                    ResultDatabase.invoke(applicationContext)
                )
            )
        )[MainViewModel::class.java]

        setAdapter()

        lifecycleScope.launch {
            viewModel.data.collect { currResultList ->
                itemRecyclerViewAdapter.differ.submitList(currResultList)
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                    firstVisibleItemPosition >= 0
                ) {
                    viewModel.fetchData()
                }
            }
        })
    }

    private fun setAdapter() {
        itemRecyclerViewAdapter = ItemRecyclerViewAdapter()
        binding.recyclerView.apply {
            adapter = itemRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
}