package com.example.wordbook.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.MainApplication
import com.example.wordbook.R
import com.example.wordbook.databinding.FragmentHomeBinding
import com.example.wordbook.model.WordRepository
import com.example.wordbook.viewmodels.HomeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var wordRepository: WordRepository

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inject
        (activity?.application as MainApplication).appComponent.injectHome(this)

        // Setup ViewModel
        val factory = HomeViewModel.Companion.Factory(wordRepository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(HomeViewModel::class.java)

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initListeners()
        initData()
        return binding.root
    }

    private fun initData() {

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wordsList.collect {
                    Log.d(TAG, "New updated list of data ${it}")

                }
            }

        }
    }

    private fun initListeners() {
        binding.btnRandomWord.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment)
        }
        binding.btnFavourites.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_favouritesFragment)
        }
        binding.btnHistory.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_historyFragment)
        }
        binding.btnSettings.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_settingsFragment)
        }

        binding.searchView.editText.doOnTextChanged { text, start, before, count ->
            viewModel.updateSearchText(text.toString())
        }
    }
}