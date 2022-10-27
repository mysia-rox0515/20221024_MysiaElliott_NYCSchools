package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.R
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.databinding.FragmentScoresBinding
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.SchoolsItem
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.model.ScoresItem
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoresFragment : Fragment() {
    private val binding by lazy {
        FragmentScoresBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.school?.let {
            populateDetails(it)
        } ?: let {
            AlertDialog.Builder(requireActivity())
                .setTitle("Error")
                .setMessage("No school details available")
                .setNegativeButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_DetailsFragment_to_SchoolsFragment)
                }
        }

        populateScores(viewModel.schoolSat())

        return binding.root
    }

    private fun populateScores(item: ScoresItem?) {
        binding.schoolMathScores.text = String.format("Math score: ${item?.satMathAvgScore ?: "Score not available"}")
        binding.schoolReadingScores.text = String.format("Reading score: ${item?.satCriticalReadingAvgScore ?: "Score not available"}")
        binding.schoolWritingScores.text = String.format("Writing score: ${item?.satWritingAvgScore ?: "Score not available"}")
    }

    private fun populateDetails(school: SchoolsItem) {
        binding.schoolName.text = school.schoolName
        binding.totalStudents.text = String.format("Total # Students: ${school.totalStudents}")
        binding.schoolWebsite.text = String.format("Website: ${school.website}")
        binding.schoolLocation.text = String.format("Location: ${school.location}")
        binding.schoolPhoneNumber.text = String.format("Phone #: ${school.phoneNumber}")
        binding.schoolOverview.text = String.format("${school.overview}")
        binding.schoolPhoneNumber.text = String.format("Phone #: ${school.phoneNumber}")
    }
}