package com.example.wordbook.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.MainApplication
import com.example.wordbook.R
import com.example.wordbook.databinding.FragmentSettingsBinding
import com.example.wordbook.utils.Constants
import com.example.wordbook.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    @Inject
    lateinit var appPreferences: SharedPreferences

    lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MainApplication).appComponent.injectSettings(this)

        val factory = SettingsViewModel.Companion.Factory(appPreferences)
        viewModel = ViewModelProvider(
            requireActivity(),
            factory
        )[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        initListeners()
        initData()

        return binding.root
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            view?.findNavController()?.navigateUp()
        }
        binding.notifyWordOfDay.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotifyWordOfTheDaySwitch(isChecked)
        }
        binding.darkTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkThemeSwitch(isChecked)
        }
        binding.history.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setHistorySwitch(isChecked)
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.notifyWordOfDay.isChecked = it.notifyWordOfDay
                    binding.darkTheme.isChecked = it.darkTheme
                    binding.history.isChecked = it.history
                    Log.d(TAG, "Got UI update request")
                }
            }

        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Setting fragment paused")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Setting fragment destroyed")
    }
}