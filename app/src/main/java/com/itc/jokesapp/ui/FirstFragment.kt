package com.itc.jokesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itc.jokesapp.R
import com.itc.jokesapp.adapter.JokeAdapter
import com.itc.jokesapp.databinding.FragmentFirstBinding
import com.itc.jokesapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint


class FirstFragment : BaseFragment() {

    private val jokesAdapter by lazy{
        JokeAdapter(){

        }
    }

    private val binding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

                    jokesAdapter.setJokes(state.response.jokes ?: emptyList())
                }

                is UIState.ERROR -> {
                    // Show error dialog

                    showError(state.error.localizedMessage){
                        jokesViewModel.getJokes()
                    }
                }

                else -> {}
            }


        }

        jokesViewModel.getJokes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}