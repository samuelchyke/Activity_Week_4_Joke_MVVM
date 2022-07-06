package com.itc.jokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itc.jokesapp.databinding.JokeCardrowViewBinding
import com.itc.jokesapp.model.Jokes

class JokeAdapter(
    private val mJokeList: MutableList<Jokes> = mutableListOf(),
    private val onJokeClicked: (Int?) -> Unit
) : RecyclerView.Adapter<MyViewHolder>(
) {

    fun setJokes(jokes: List<Jokes>) {
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
        holder.bind(mJokeList[position], onJokeClicked)

    override fun getItemCount(): Int  = mJokeList.size

}

class MyViewHolder (
    private val binding: JokeCardrowViewBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(joke: Jokes, onJokeClicked: (Int?) -> Unit)
    {
        binding.jokeTxtVw.text = joke.joke

        itemView.setOnClickListener{
            onJokeClicked(joke.id)
        }
    }

}