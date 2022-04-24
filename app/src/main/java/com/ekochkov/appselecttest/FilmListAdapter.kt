package com.ekochkov.appselecttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekochkov.appselecttest.databinding.LayoutFilmItemBinding

class FilmListAdapter: RecyclerView.Adapter<FilmListAdapter.FilmHolder>() {

    var filmList = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_film_item, parent, false)
        return FilmHolder(view)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = filmList[position]
        holder.binding.title.text = film.title
        holder.binding.description.text = film.description

        Glide.with(holder.itemView)
                .load(film.poster)
                .centerCrop()
                .into(holder.binding.poster)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    class FilmHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = LayoutFilmItemBinding.bind(itemView)
    }
}