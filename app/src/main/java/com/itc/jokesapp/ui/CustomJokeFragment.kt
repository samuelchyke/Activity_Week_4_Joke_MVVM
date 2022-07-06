package com.itc.jokesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.itc.jokesapp.R
import com.itc.jokesapp.adapter.JokeAdapter
import com.itc.jokesapp.databinding.FragmentCustomJokeBinding
import com.itc.jokesapp.util.UIState


class CustomJokeFragment : BaseFragment() {

    private val jokesAdapter by lazy{
        JokeAdapter()
    }

    private val binding by lazy {
        FragmentCustomJokeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        initRecyclerView()

        var firstName = "Chuck"
        var lastName = "Norris"

        observeData(firstName,lastName)

        binding.btnCustomJoke.setOnClickListener {

            firstName = binding.editTxtFirstName.text.toString()
            lastName = binding.editTxtLastName.text.toString()

            observeData(firstName,lastName)
        }



        return binding.root
    }

    private fun initRecyclerView(){
        //Recycler View
        binding.cstJokesRecVw.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesAdapter
        }
    }

    private fun observeData(firstName:String,lastName:String){
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
                        jokesViewModel.getCustomJokes(firstName,lastName)
                    }
                }

                else -> {}
            }


        }

        jokesViewModel.getCustomJokes(firstName,lastName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.let {
            null
        }
    }

}