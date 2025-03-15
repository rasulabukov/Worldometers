package com.example.worldometers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopulationViewModel @Inject constructor(
    private val repository: PopulationRepository
) : ViewModel() {

    private val _populationData = MutableLiveData<PopulationData>()
    val populationData: LiveData<PopulationData> get() = _populationData

    init {
        fetchPopulation()
    }

    private fun fetchPopulation() {
        viewModelScope.launch {
            val data = repository.fetchPopulationData()
            _populationData.value = data
        }
    }
}