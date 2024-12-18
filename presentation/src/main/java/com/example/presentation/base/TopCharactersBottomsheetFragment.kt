package com.example.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.databinding.BottomSheetLayoutBinding
import com.example.presentation.main.GameGenreViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import android.os.Bundle as Bundle1

class TopCharacterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameGenreViewModel

    private var topCharacters: String = "No data available"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle1?
    ): View? {
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(GameGenreViewModel::class.java)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topCharacters.collect { characterData ->
                    topCharacters = characterData
                    binding.tvTopCharacters.text = topCharacters
                }
            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): TopCharacterBottomSheetFragment {
            return TopCharacterBottomSheetFragment()
        }
    }
}
