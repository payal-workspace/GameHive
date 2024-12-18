package com.example.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.adapters.CarouselAdapter
import com.example.presentation.adapters.SportsCategoriesItemsAdapter
import com.example.presentation.base.BaseActivity
import com.example.presentation.base.TopCharacterBottomSheetFragment
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameMainActivity : BaseActivity<GameGenreViewModel, ActivityMainBinding>() {

    private lateinit var sportsAdapter: CarouselAdapter
    private lateinit var sportsCategoryItemAdapter: SportsCategoriesItemsAdapter

    override val viewModel: GameGenreViewModel by viewModels()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        observeViewModel()
    }

    private fun initUI() {
        sportsAdapter = CarouselAdapter().also {
            binding.carouselView.adapter = it
        }
        binding.searchBar.setOnQueryTextListener(searchTextListener)
        binding.fabShowBottomSheet.setOnClickListener { viewModel.showBottomSheet() }


        binding.sportsCategoryList.layoutManager = LinearLayoutManager(this@GameMainActivity)
        sportsCategoryItemAdapter = SportsCategoriesItemsAdapter().also {
            binding.sportsCategoryList.adapter = it
        }

    }

    private val searchTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            binding.searchBar.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let { viewModel.onSearchQueryChanged(newText) }
            return true
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeFilteredCategories() }
                launch { sportsCategoryItemsLists() }
                launch { observeLoadingState() }
                launch { observeErrorState() }
                launch { observeBottomSheetTrigger() }
            }
        }
    }

    private suspend fun observeFilteredCategories() {
        viewModel.filteredCategories.collect { categories ->
            sportsAdapter.submitList(categories)
        }

    }
    private suspend fun sportsCategoryItemsLists(){
        viewModel.sportsCategoriesLists.collect { sportsLists ->
            sportsCategoryItemAdapter.submitList(sportsLists)
        }
    }

    private suspend fun observeLoadingState() {
        viewModel.loading.collect { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    private suspend fun observeErrorState() {
        viewModel.error.collect { error ->
            showError(error.message)
        }
    }

    private suspend fun observeBottomSheetTrigger() {
        viewModel.showBottomSheet.collect { shouldShow ->
            if (shouldShow) showStatisticsBottomSheet()
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    private fun showStatisticsBottomSheet() {
        val bottomSheet = TopCharacterBottomSheetFragment.newInstance()
        bottomSheet.show(supportFragmentManager, "StatisticsBottomSheet")
    }
}


