package com.example.wordbook.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.MainApplication
import com.example.wordbook.databinding.FragmentDetailBinding
import com.example.wordbook.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var PARAM_WORD_ID: Int? = null
    private var PARAM_WORD_TEXT: String? = null

    private lateinit var binding: FragmentDetailBinding

    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inject
        // (activity?.application as MainApplication).appComponent?.injectDetails(this)
        // Retrieve Arguments
        PARAM_WORD_ID = arguments?.getInt(PARAM_NAME_WORD_ID)
        PARAM_WORD_TEXT = arguments?.getString(PARAM_NAME_WORD_TEXT)

        // Initialize/Retrieve view model
        // viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        Log.d(TAG, "View Model instance $viewModel")

        Log.d(TAG, "Data recevied in $PARAM_WORD_ID, $PARAM_WORD_TEXT")
    }

    private fun initData() {
        if (PARAM_WORD_ID != null) {
            lifecycleScope.launch {
                viewModel.getWordDetailById(PARAM_WORD_ID!!).collect {
                    Log.d(TAG, "Retrieved the data $it")
                    binding.topAppBarCollapsing.title = it?.word
                    binding.wordMeaning.text = it?.meaning
                }
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