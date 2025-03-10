package com.finki.gymapp.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.finki.gymapp.R
import com.finki.gymapp.databinding.WorkoutGridItemBinding
import com.finki.gymapp.model.Workout

class GridItemViewHolder(private val binding: WorkoutGridItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener { view ->
            binding.workout?.let { workout ->
                navigateToDetail(view, workout)
            }
        }
    }

    private fun navigateToDetail(view: View, workout: Workout) {
        val args = Bundle()
        args.putParcelable("workout", workout)
        view.findNavController().navigate(R.id.navigation_detail, args)
    }

    fun bind(item: Workout) {
        binding.apply {
            workout = item
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): GridItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = WorkoutGridItemBinding.inflate(layoutInflater, parent, false)
            return GridItemViewHolder(binding)
        }
    }
}