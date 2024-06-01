package com.example.wordbook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wordbook.R
import com.example.wordbook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        initListeners()
        return binding.root
    }

    private fun initListeners(){
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
    }
}