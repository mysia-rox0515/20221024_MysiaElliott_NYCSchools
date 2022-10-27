package com.bignerdranch.android.a20221024_mysiaelliott_nycschools.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.R
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.adapter.SchoolAdapter
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.databinding.FragmentSchoolsBinding
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.utils.UIState
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolsFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolsBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private val schoolAdapter by lazy {
        SchoolAdapter {
            viewModel.school = it
            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.schoolRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = schoolAdapter
        }

        viewModel.schools.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.schoolRv.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    schoolAdapter.updateSchools(state.schools)
                    binding.loadingSpinner.visibility = View.GONE
                    binding.schoolRv.visibility = View.VISIBLE
                }
                is UIState.ERROR -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.schoolRv.visibility = View.VISIBLE

                    AlertDialog.Builder(requireActivity())
                        .setTitle("ERROR: DATA NOT UP-TO-DATE")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                }
            }
        }

        return binding.root
    }
}