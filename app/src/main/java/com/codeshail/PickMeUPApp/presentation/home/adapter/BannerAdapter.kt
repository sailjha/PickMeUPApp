package com.codeshail.pickmeupapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeshail.pickmeupapp.databinding.ItemBannerBinding
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.Constants.TMDB_IMAGE_BASE_URL_W780
import com.codeshail.pickmeupapp.utils.DiffUtilCallback
import com.codeshail.pickmeupapp.utils.getMovieGenreListFromIds
import com.codeshail.pickmeupapp.utils.loadImage

class BannerAdapter(
) : ListAdapter<MovieResult, BannerAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResponseResult: MovieResult) = binding.apply {

            bannerTitle.text = movieResponseResult.title
            bannerGenres.text = getMovieGenreListFromIds(
                movieResponseResult.genreIds
            ).joinToString(" • ") { it.name }

            bannerImage.loadImage(TMDB_IMAGE_BASE_URL_W780.plus(movieResponseResult.backdropPath))

        }
    }
}
