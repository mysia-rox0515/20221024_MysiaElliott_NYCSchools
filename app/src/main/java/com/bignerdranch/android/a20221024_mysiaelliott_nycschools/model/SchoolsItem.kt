package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model


import com.google.gson.annotations.SerializedName

data class SchoolsItem(
    @SerializedName("dbn")
    val dbn: String?,
    @SerializedName("school_name")
    val schoolName: String?,
    @SerializedName("total_students")
    val totalStudents: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state_code")
    val stateCode: String?,
    @SerializedName("zip")
    val zip: Int,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("fax_number")
    val faxNumber: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("overview_paragraph")
    val overview: String? = null
)
