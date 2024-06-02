package com.example.wordbook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wordbook.databinding.FragmentFavouritesBinding

class FavouritesFragment: Fragment() {
    private lateinit var binding: FragmentFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container,false)
        initListeners()
        return binding.root
    }

    private fun initListeners(){
        binding.topAppBar.setNavigationOnClickListener {
            view?.findNavController()?.navigateUp()
        }
    }
}