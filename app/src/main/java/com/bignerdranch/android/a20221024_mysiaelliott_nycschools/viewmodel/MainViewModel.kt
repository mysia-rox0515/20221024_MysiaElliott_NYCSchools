package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.SchoolsItem
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.ScoresItem
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.networking.Repository
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getSchools()
        getScores()
    }

    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private var scores: List<ScoresItem>? = null
    var school: SchoolsItem? = null

    private fun getSchools() {
        viewModelScope.launch {
            repository.getAllSchools().collect {
                _schools.postValue(it)
            }
        }
    }

    private fun getScores() {
        viewModelScope.launch {
            repository.getSatScore().collect {
                scores = it
            }
        }
    }

    fun schoolSat(): ScoresItem? =
        scores?.firstOrNull { it.dbn == school?.dbn }
}