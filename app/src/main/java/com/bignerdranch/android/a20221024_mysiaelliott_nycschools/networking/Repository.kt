package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.networking

import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.ScoresItem
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface Repository {

    fun getAllSchools(): Flow<UIState>
    fun getSatScore():  Flow<List<ScoresItem>>

}

class RepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    override fun getAllSchools(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = serviceApi.getAllSchools()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                } ?: throw Exception("Response for schools is null")
            } else {
                throw Exception("Response is a failure")
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }.flowOn(ioDispatcher)

    override fun getSatScore(): Flow<List<ScoresItem>> = flow {
        try {
            val response = serviceApi.getSatScores()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: throw Exception("Response for schools is null")
            } else {
                throw Exception("Response is a failure")
            }
        } catch (e: Exception) {
            emit(emptyList())
        }

    }
}