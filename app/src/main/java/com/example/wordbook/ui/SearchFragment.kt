package com.example.wordbook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wordbook.databinding.FragmentSearchBinding
import com.google.android.material.search.SearchView

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        initListeners()
        return binding.root
    }

    private fun initListeners(){
//        binding.searchView.setVisible(true)
        binding.searchView.show()
        binding.searchView.addTransitionListener { searchView, transitionState, transitionState2 ->
            if(transitionState2 == SearchView.TransitionState.HIDDEN){
                view?.findNavController()?.navigateUp()
            }
        }

    }
}