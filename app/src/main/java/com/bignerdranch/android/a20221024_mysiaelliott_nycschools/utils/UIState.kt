package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.utils

import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.SchoolsItem
import java.lang.Exception

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val schools: List<SchoolsItem>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
