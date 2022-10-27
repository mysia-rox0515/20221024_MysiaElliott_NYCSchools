package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.databinding.FragmentSchoolsBinding
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.databinding.SchoolItemBinding
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.SchoolsItem

class SchoolAdapter(
    private val schoolsData: MutableList<SchoolsItem> = mutableListOf(),
    private val onClickSchool: (SchoolsItem) -> Unit
) : RecyclerView.Adapter<SchoolViewHolder>() {

    fun updateSchools(newSet: List<SchoolsItem>) {
        schoolsData.clear()
        schoolsData.addAll(newSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SchoolViewHolder(
            SchoolItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) =
        holder.bind(schoolsData[position], onClickSchool)

    override fun getItemCount(): Int = schoolsData.size
}

class SchoolViewHolder(private val binding: SchoolItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(schoolsItem: SchoolsItem, onClickSchool: (SchoolsItem) -> Unit) {

        binding.schoolName.text = schoolsItem.schoolName
        binding.schoolCity.text = String.format(
            "Location: ${schoolsItem.city}, ${schoolsItem.stateCode} ${schoolsItem.zip}")
        binding.schoolWebsite.text = schoolsItem.website

        itemView.setOnClickListener {
            onClickSchool(schoolsItem)
        }

    }
}