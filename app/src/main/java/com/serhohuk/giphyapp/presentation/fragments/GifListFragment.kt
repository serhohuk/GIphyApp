package com.serhohuk.giphyapp.presentation.fragments

import android.os.Bundle
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GifListFragment : Fragment() {

    private var _binding : FragmentGifListBinding? = null
    private val binding get() = _binding!!

    private val useCase by inject<TrendingGifUseCase>()
    private lateinit var adapter: GifListAdapter

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

        adapter = GifListAdapter()
        binding.recView.adapter = adapter
        val layoutManager = StaggeredGridLayoutManager( 2,GridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        binding.recView.layoutManager = layoutManager


        lifecycleScope.launch {
            val response = useCase.execute(26,0)
            if (response is Resource.Success){
                adapter.listDiffer.submitList(response.data!!.list)
            } else {
                Toast.makeText(requireContext(), "error",Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun load(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}