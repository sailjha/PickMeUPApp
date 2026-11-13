package com.codeshail.pickmeupapp.presentation.search


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeshail.pickmeupapp.R
import com.codeshail.pickmeupapp.databinding.ItemTopMovieBinding
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.Constants
import com.codeshail.pickmeupapp.utils.DiffUtilCallback
import com.codeshail.pickmeupapp.utils.isNetworkAvailable
import com.codeshail.pickmeupapp.utils.loadImage
import com.codeshail.pickmeupapp.utils.showToast

class TopSearchesAdapter(
    private var onMovieClick: (movieResult: MovieResult) -> Unit
) : ListAdapter<MovieResult, TopSearchesAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemTopMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResponseResult: MovieResult) = binding.apply {
            val context = binding.root.context
            movieImage.loadImage(Constants.TMDB_POSTER_IMAGE_BASE_URL_W342.plus(movieResponseResult.backdropPath))
            movieNameText.text = movieResponseResult.title
            root.setOnClickListener {
                if (isNetworkAvailable(binding.root.context)) {
                    onMovieClick(movieResponseResult)
                } else {
                    showToast(context, context.getString(R.string.internet_connection_required))
                }
            }
        }
    }
}
