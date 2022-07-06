package com.itc.jokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itc.jokesapp.databinding.JokeCardrowViewBinding
import com.itc.jokesapp.model.Jokes

class JokeAdapter(
    private val mJokeList: MutableList<Jokes> = mutableListOf()
) : RecyclerView.Adapter<MyViewHolder>(
) {

    fun setJokes(jokes: MutableList<Jokes>) {
        mJokeList.clear()
        mJokeList.addAll(jokes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder =
        MyViewHolder(
            JokeCardrowViewBinding.inflate(
                LayoutInflater.from(parent.context ),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(mJokeList[position])

    override fun getItemCount(): Int  = mJokeList.size

}

class MyViewHolder (
    private val binding: JokeCardrowViewBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(joke: Jokes)
    {
        binding.jokeTxtVw.text = joke.joke
    }

}