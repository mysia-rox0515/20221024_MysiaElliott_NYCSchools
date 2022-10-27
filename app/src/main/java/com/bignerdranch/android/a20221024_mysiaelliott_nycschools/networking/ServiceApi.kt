package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.networking

import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.SchoolsItem
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.ScoresItem
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET(ALL_SCHOOLS)
    suspend fun getAllSchools(): Response<List<SchoolsItem>>

    @GET(SCORES)
    suspend fun getSatScores(): Response<List<ScoresItem>>

    companion object {
        // https://data.cityofnewyork.us/resource/f9bf-2cp4.json

        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        private const val ALL_SCHOOLS = "s3k6-pzi2.json"
        private const val SCORES = "f9bf-2cp4.json"
    }
}