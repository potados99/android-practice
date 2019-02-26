package com.potados.practice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.support.design.widget.VisibilityAwareImageButton
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.TextView
import com.potados.practice.data.BGMovie
import com.potados.practice.data.BGMovieProvider

import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private val provider: BGMovieProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        setSupportActionBar(toolbar)

        toolbar.title = title

        twoPane = (detail_fragment_container != null).apply {
            if (!this) {
                // Small screen.
                info_fab.setOnClickListener { view ->
                    Snackbar.make(view, "Help?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            else {
                // Wide screen.
                // info_fab.visibility = View.INVISIBLE
            }
        }

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, provider.moviesInList, twoPane)
    }
}
