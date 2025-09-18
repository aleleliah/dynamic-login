package com.fyp2.securegallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fyp2.securegallery.R

class ThumbnailAdapter(
    private val thumbnails: List<String> // List of image files
) : RecyclerView.Adapter<ThumbnailAdapter.ThumbnailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thumbnail, parent, false)
        return ThumbnailViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        val file = thumbnails[position]
        holder.bind(file)
    }

    override fun getItemCount(): Int = thumbnails.size

    class ThumbnailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewThumbnail)

        fun bind(file: String) {
            imageView.load(file) {
                placeholder(R.drawable.pin_circle_filled)
                error(R.drawable.ic_launcher_foreground)
                crossfade(true)
            }
        }
    }
}
