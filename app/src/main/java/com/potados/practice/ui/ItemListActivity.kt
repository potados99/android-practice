package com.potados.practice.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.View
import com.potados.practice.data.movie.BGMovieProvider
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel

import kotlinx.android.synthetic.main.activity_bgmovie_list.*
import kotlinx.android.synthetic.main.bgmovie_list.*
import org.koin.android.ext.android.inject
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.potados.practice.R
import com.potados.practice.data.storage.StorageInfoProvider


class ItemListActivity : AppCompatActivity() {

    private val movieProvider: BGMovieProvider by inject()
    private val storageProvider: StorageInfoProvider by inject()
    private val vm: ItemDetailFragmentViewModel by inject() /* to share global.. no better idea.. :( */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bgmovie_list)
        setSupportActionBar(toolbar)

        toolbar.title = this.title

        val twoPane: Boolean = (detail_fragment_container != null).apply {
            if (!this) {
                // Small screen.
                info_fab.setOnClickListener { view ->
                    Snackbar.make(view, "Help?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            else {
                // Wide screen.
                info_fab.visibility = View.INVISIBLE
            }
        }

        with(vm) {
            setTwoPane(twoPane)
        }

        setupRecyclerView(item_list, twoPane)
    }


    private fun setupRecyclerView(recyclerView: RecyclerView, twoPane: Boolean) {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                LinearLayoutManager(this).orientation
            ))
        recyclerView.adapter =
            BGMovieItemRecyclerViewAdapter(this, movieProvider.moviesInList, twoPane)
    }

}
