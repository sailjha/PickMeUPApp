package com.codeshail.pickmeupapp.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeshail.pickmeupapp.databinding.ItemPosterBinding
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.Constants
import com.codeshail.pickmeupapp.utils.DiffUtilCallback
import com.codeshail.pickmeupapp.utils.isNetworkAvailable
import com.codeshail.pickmeupapp.utils.loadImage

class RecommendationsAdapter(
    private var onPosterClick: ((movieResult: MovieResult) -> Unit)
) : ListAdapter<MovieResult, RecommendationsAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(
        private val binding: ItemPosterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResult: MovieResult) = binding.apply {
            posterImage.loadImage(Constants.TMDB_POSTER_IMAGE_BASE_URL_W342.plus(movieResult.posterPath))
            ratingText.text = String.format("%.1f", movieResult.voteAverage)
            root.setOnClickListener {
                if (isNetworkAvailable(binding.root.context)) {
                    onPosterClick.invoke(movieResult)
                }
            }
        }
    }
}