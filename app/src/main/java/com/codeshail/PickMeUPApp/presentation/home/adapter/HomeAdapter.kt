package com.codeshail.pickmeupapp.presentation.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeshail.pickmeupapp.databinding.ItemFeedHorizontalListBinding
import com.codeshail.pickmeupapp.domain.models.HomeFeed
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.DiffUtilCallback

class HomeAdapter(
    private var onPosterClick: (movieResult: MovieResult) -> Unit
) : ListAdapter<HomeFeed, HomeAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemFeedHorizontalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemFeedHorizontalListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeFeed: HomeFeed) = binding.apply {
            title.text = homeFeed.title
            // Setting up inner horizontal recyclerview

            HorizontalAdapter(
                // Poster click
                onPosterClick = onPosterClick
            ).let {
                recyclerviewPostersList.adapter = it
                it.submitList(homeFeed.list)
            }
        }
    }
}
