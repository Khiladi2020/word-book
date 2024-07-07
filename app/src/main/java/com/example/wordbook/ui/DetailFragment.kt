package com.example.wordbook.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.MainApplication
import com.example.wordbook.R
import com.example.wordbook.databinding.FragmentDetailBinding
import com.example.wordbook.model.WordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailFragment : Fragment() {
    private var PARAM_WORD_ID: Int? = null
    private var PARAM_WORD_TEXT: String? = null

    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var wordRepository: WordRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inject
        (activity?.application as MainApplication).appComponent?.injectDetails(this)
        // Retrieve Arguments
        PARAM_WORD_ID = arguments?.getInt(PARAM_NAME_WORD_ID)
        PARAM_WORD_TEXT = arguments?.getString(PARAM_NAME_WORD_TEXT)

        Log.d(TAG, "Data recevied in $PARAM_WORD_ID, $PARAM_WORD_TEXT")
    }

    private fun initData(){
        if(PARAM_WORD_ID != null){
            lifecycleScope.launch {
                val word = wordRepository.getWordWithId(PARAM_WORD_ID!!)
                Log.d(TAG, "Retrieved the data $word")
                binding.topAppBarCollapsing.title = word?.word
                binding.wordMeaning.text = word?.meaning
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        initListeners()
        initData()
        return binding.root
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            view?.findNavController()?.navigateUp()
        }
    }

    companion object {
        const val PARAM_NAME_WORD_ID = "word_id";
        const val PARAM_NAME_WORD_TEXT = "word_text";
    }
}