package com.itc.jokesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itc.jokesapp.repository.JokesRepo
import com.itc.jokesapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val jokesRepo: JokesRepo
) : ViewModel() {

    private val _jokes: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val jokes: LiveData<UIState> get() = _jokes

    fun getJokes() {
        //Global
        CoroutineScope(Dispatchers.IO).launch {
            // WORKER THREAD
            try {
                val response = jokesRepo.getJokes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.Main) {
                            //MAIN THREAD
                            //UPDATE UI
                            withContext(Dispatchers.IO){
                                //worker thread
                                _jokes.postValue(UIState.SUCCESS(it))
                            }

                            //main thread
//                            _jokes.value = UIState.SUCCESS(it)

                        }

                    } ?: throw Exception("Data Null")
                } else {
                    throw Exception(response.errorBody()?.string())
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    //MAIN THREAD
                    //UPDATE UI
                    _jokes.postValue(UIState.ERROR(e))

                }

            }
            val response = jokesRepo.getJokes()
        }

    }

    fun getRandomJokes() {
        //Global
        CoroutineScope(Dispatchers.IO).launch {
            // WORKER THREAD
            try {
                val response = jokesRepo.getRandomJokes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.Main) {
                            //MAIN THREAD
                            //UPDATE UI

                            //worker thread
                            _jokes.postValue(UIState.SUCCESS(it))

                            //main thread
                            //_characters.value = it

                        }

                    } ?: throw Exception("Data Null")
                } else {
                    throw Exception(response.errorBody()?.string())
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    //MAIN THREAD
                    //UPDATE UI
                    _jokes.postValue(UIState.ERROR(e))

                }

            }
            val response = jokesRepo.getRandomJokes()
        }

    }

    fun getCustomJokes(firstName:String, lastName:String) {
        //Global
        CoroutineScope(Dispatchers.IO).launch {
            // WORKER THREAD
            try {
                val response = jokesRepo.getCustomJoke(firstName,lastName)
                if (response.isSuccessful) {
                    response.body()?.let {
//                        _jokes.postValue(UIState.SUCCESS(it))
                        withContext(Dispatchers.Main) {
                            //MAIN THREAD
                            //UPDATE UI
                            //main thread
                            _jokes.value = UIState.SUCCESS(it)

                        }

                    } ?: throw Exception("Data Null")
                } else {
                    throw Exception(response.errorBody()?.string())
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    //MAIN THREAD
                    //UPDATE UI
                    _jokes.postValue(UIState.ERROR(e))

                }

            }
            val response = jokesRepo.getCustomJoke(firstName,lastName)
        }

    }

    public override fun onCleared() {
        super.onCleared()
    }
}