package com.meeweel.newstapeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.newstapeapp.data.NewsTapeState
import com.meeweel.newstapeapp.databinding.NewsTapeFragmentLayoutBinding

class NewsTapeFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }
    private var _binding: NewsTapeFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = NewsTapeFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsTapeFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.newsTapeRecycler.adapter = adapter
        val observer = Observer<NewsTapeState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
//        viewModel.getNewsFromLocalSource()

        binding.swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light
        )
        binding.swipeRefresh.setOnRefreshListener {
            TODO()
        }
    }

    private fun renderData(data: NewsTapeState) = when (data) {
        is NewsTapeState.Success -> {
            val newsData = data.newsData
            binding.loadingLayout.visibility = View.GONE
            adapter.setNewsData(newsData)
        }
        is NewsTapeState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is NewsTapeState.Error -> {
            binding.loadingLayout.visibility = View.GONE

        }
    }

    companion object {
        fun newInstance() = NewsTapeFragment()
    }
}