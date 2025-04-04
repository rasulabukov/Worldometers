package com.example.worldometers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopulationViewModel : ViewModel() {
    companion object {
        private const val TAG = "PopulationViewModel"
    }

    private val repository = PopulationRepository()
    private val _populationData = MutableLiveData<PopulationData>()
    val populationData: LiveData<PopulationData> get() = _populationData

    init {
        fetchPopulation()
    }

    private fun fetchPopulation() {
        viewModelScope.launch {
            try {
                _populationData.value = PopulationData("Loading...")

                val result = withContext(Dispatchers.IO) {
                    repository.fetchPopulationData()
                }

                _populationData.value = result
            } catch (e: Exception) {
                Log.e(TAG, "Error in ViewModel", e)
                _populationData.value = PopulationData("Error: ${e.message}")
            }
        }
    }
}