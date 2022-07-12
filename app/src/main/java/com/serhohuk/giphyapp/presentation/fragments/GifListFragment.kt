package com.serhohuk.giphyapp.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.databinding.FragmentGifListBinding
import com.serhohuk.giphyapp.domain.usecase.TrendingGifUseCase
import com.serhohuk.giphyapp.presentation.adapters.GifListAdapter
import com.serhohuk.giphyapp.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class GifListFragment : Fragment() {

    private var _binding : FragmentGifListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModel()
    private lateinit var adapter: GifListAdapter
    private var offset = 0
    private var jobSearch : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        viewModel.getTrendingGif(offset)




        binding.etSearch.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                jobSearch?.cancel()
                if (text.isNotEmpty()){
                    jobSearch = lifecycleScope.launch {
                        delay(500)
                        viewModel.getSearchGif(text,offset)
                    }
                } else {
                    viewModel.getTrendingGif(offset)
                }
            }
        })

        lifecycleScope.launchWhenCreated {
            viewModel.gifsData.collectLatest {
                when(it){
                    is Resource.Success ->{
                        adapter.listDiffer.submitList(it.data!!.list)
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Error ->{
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


   fun setupAdapter(){
       adapter = GifListAdapter()
       binding.recView.adapter = adapter
       val layoutManager = StaggeredGridLayoutManager( 2,GridLayoutManager.VERTICAL)
       layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
       binding.recView.layoutManager = layoutManager
   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}