package com.codeshail.pickmeupapp.presentation.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeshail.pickmeupapp.R
import com.codeshail.pickmeupapp.databinding.ItemPosterBinding
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.Constants.TMDB_POSTER_IMAGE_BASE_URL_W342
import com.codeshail.pickmeupapp.utils.DiffUtilCallback
import com.codeshail.pickmeupapp.utils.isNetworkAvailable
import com.codeshail.pickmeupapp.utils.loadImage
import com.codeshail.pickmeupapp.utils.showToast

class HorizontalAdapter(
    private var onPosterClick: ((movieResult: MovieResult) -> Unit)? = null
) : ListAdapter<MovieResult, HorizontalAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemPosterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResponseResult: MovieResult) = binding.apply {
            val context = binding.root.context
            posterImage.loadImage(TMDB_POSTER_IMAGE_BASE_URL_W342.plus(movieResponseResult.posterPath))
            ratingText.text = String.format("%.1f", movieResponseResult.voteAverage)
            posterImage.setOnClickListener {
                if (isNetworkAvailable(binding.root.context)) {
                    onPosterClick?.invoke(movieResponseResult)
                } else {
                    showToast(context, context.getString(R.string.internet_connection_required))
                }
            }
        }
    }
}
