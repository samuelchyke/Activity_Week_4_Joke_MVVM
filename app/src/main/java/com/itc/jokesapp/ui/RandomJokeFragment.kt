package com.itc.jokesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.itc.jokesapp.adapter.JokeAdapter
import com.itc.jokesapp.databinding.FragmentRandomJokeBinding
import com.itc.jokesapp.util.UIState


class RandomJokeFragment : BaseFragment() {

    private val jokesAdapter by lazy{
        JokeAdapter()
    }

    private val binding by lazy {
        FragmentRandomJokeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initRecyclerView()
        observeData()

        return binding.root
    }

    private fun initRecyclerView(){
        //Recycler View
        binding.jokesRecVw.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesAdapter
        }
    }

    private fun observeData(){
        jokesViewModel.jokes.observe(viewLifecycleOwner) { state ->

            when (state) {

                is UIState.LOADING -> {
                    //Load Spinner
                }

                is UIState.SUCCESS -> {
                    // Update adapter
//                    binding.jokesRecVw.visibility = View.VISIBLE

                    jokesAdapter.setJokes(state.response.jokes ?: mutableListOf())
                }

                is UIState.ERROR -> {
                    // Show error dialog

                    showError(state.error.localizedMessage){
                        jokesViewModel.getRandomJokes()
                    }
                }

                else -> {}
            }


        }

        jokesViewModel.getRandomJokes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.let {
            null
        }
    }
}