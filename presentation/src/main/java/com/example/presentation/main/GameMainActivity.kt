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
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.presentation.R
import com.example.presentation.adapters.CarouselAdapter
import com.example.presentation.adapters.SportsCategoryItemAdapter
import com.example.presentation.base.BaseActivity
import com.example.presentation.base.TopCharacterBottomSheetFragment
import com.example.presentation.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameMainActivity : BaseActivity<GameGenreViewModel, ActivityMainBinding>() {

    override val viewModel: GameGenreViewModel by viewModels()

    private val sportsAdapter by lazy { CarouselAdapter() }
    private val sportsCategoryItemAdapter by lazy { SportsCategoryItemAdapter() }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    companion object {
        const val TAG_BOTTOMSHEET = "StatisticsBottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() = with(binding) {
        setupAdapters()
        setupViewPagerWithTabLayout()
        setupListeners()
        setupRecyclerViewScrollListener()
    }

    private fun setupAdapters() {
        binding.sportsCategoryList.apply {
            adapter = sportsCategoryItemAdapter
            layoutManager = LinearLayoutManager(this@GameMainActivity)
        }
    }

    private fun setupViewPagerWithTabLayout() {
        binding.carouselView.adapter = sportsAdapter
        TabLayoutMediator(binding.indicator, binding.carouselView) { _, _ -> }.attach()
    }

    private fun setupRecyclerViewScrollListener() {
        binding.sportsCategoryList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                findViewById<AppBarLayout>(R.id.app_bar).apply {
                    setExpanded(dy <= 0, true)
                }
            }
        })
    }

    private fun setupListeners() {
        binding.apply {
            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = true

                override fun onQueryTextChange(newText: String?): Boolean {
                    val currentPage = carouselView.currentItem
                    viewModel.onSearchQueryChanged(newText.orEmpty(), currentPage)
                    return true
                }
            })

            fabShowBottomSheet.setOnClickListener { viewModel.showBottomSheet() }

            carouselView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.updateCategoryItems(position)
                    searchBar.apply {
                        setQuery("", false)
                        clearFocus()
                    }
                    sportsCategoryList.scrollToPosition(0)
                }
            })
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeFilteredCategories() }
                launch { observeSportsCategoryLists() }
                launch { observeLoadingState() }
                launch { observeErrorState() }
                launch { observeBottomSheetState() }
            }
        }
    }

    private suspend fun observeFilteredCategories() {
        viewModel.filteredCategories.collectLatest { categories ->
            sportsAdapter.submitList(categories)
            binding.sportsCategoryList.scrollToPosition(0)
        }
    }

    private suspend fun observeSportsCategoryLists() {
        viewModel.sportsCategoriesLists.collectLatest { items ->
            sportsCategoryItemAdapter.submitList(items)
            binding.sportsCategoryList.scrollToPosition(0)
        }
    }

    private suspend fun observeLoadingState() {
        viewModel.loading.collectLatest { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    private suspend fun observeErrorState() {
        viewModel.error.collectLatest { error ->
            error.message?.let { showToast(it) }
        }
    }

    private suspend fun observeBottomSheetState() {
        viewModel.showBottomSheet.collect {
            showStatisticsBottomSheet()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showStatisticsBottomSheet() {
        TopCharacterBottomSheetFragment.newInstance()
            .show(supportFragmentManager, TAG_BOTTOMSHEET)
    }
}





