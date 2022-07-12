package com.serhohuk.giphyapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.serhohuk.giphyapp.databinding.FragmentGifPreviewBinding
import com.serhohuk.giphyapp.presentation.adapters.GifListAdapter
import com.serhohuk.giphyapp.presentation.utils.AdapterType
import com.serhohuk.giphyapp.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GifPreviewFragment : Fragment() {

    private var _binding : FragmentGifPreviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by sharedViewModel()
    private lateinit var adapter: GifListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifPreviewBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GifListAdapter(AdapterType.PREVIEW_ADAPTER)
        binding.viewPager.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.gifsData.collectLatest(adapter::submitData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}