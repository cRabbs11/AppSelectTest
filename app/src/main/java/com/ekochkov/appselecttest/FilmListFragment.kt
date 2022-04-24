package com.ekochkov.appselecttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekochkov.appselecttest.databinding.FragmentFilmListBinding
import com.ekochkov.appselecttest.utils.*

class FilmListFragment: Fragment() {

    private lateinit var binding: FragmentFilmListBinding
    private lateinit var filmAdapter: FilmListAdapter
    private var filmList = listOf<Film>()
    private val viewModel: FilmListFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filmListLiveData.observe(viewLifecycleOwner, { list ->
            filmList = list
            updateRecyclerView(filmList)
        })

        filmAdapter = FilmListAdapter()
        binding.recyclerView.adapter = filmAdapter
        binding.recyclerView.apply { 
            adapter = filmAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = (binding.recyclerView.layoutManager as LinearLayoutManager)
                    val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                    viewModel.getLastVisibleFilmInList(lastVisiblePosition)
                }
            })
        }
        
    }

    private fun updateRecyclerView(films: List<Film>) {
        val diff = FilmDiff(filmAdapter.filmList, films)
        val diffResult = DiffUtil.calculateDiff(diff)
        filmAdapter.filmList.clear()
        filmAdapter.filmList.addAll(films)
        diffResult.dispatchUpdatesTo(filmAdapter)
    }
}