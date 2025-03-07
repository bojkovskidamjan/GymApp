package com.finki.gymapp.ui.legs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.finki.gymapp.R
import com.finki.gymapp.databinding.FragmentLegsBinding
import com.finki.gymapp.ui.common.WorkoutAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LegsFragment : Fragment() {
    private val legsViewModel: LegsViewModel by navGraphViewModels(R.id.mobile_navigation) { defaultViewModelProviderFactory }
    private lateinit var binding: FragmentLegsBinding
    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLegsBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = legsViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = WorkoutAdapter(true)
        binding.apply {
            legList.adapter = adapter
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            legList.addItemDecoration(decoration)
        }
    }
}