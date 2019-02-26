package com.potados.practice

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potados.practice.data.BGMovie
import com.potados.practice.data.BGMovieDescriptor
import com.potados.practice.data.BGMovieProvider
import kotlinx.android.synthetic.main.activity_item_detail.view.*
import kotlinx.android.synthetic.main.item_detail.view.*
import org.koin.android.ext.android.inject

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private var item: BGMovie? = null
    private val provider: BGMovieProvider by inject()
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // get selected item and set toolbar title.
                item = provider.getValidMap()?.get(it.getInt(ARG_ITEM_ID))
                // activity?.toolbar_layout?.title = item?.title ?: "NULL"
            }
            if (it.containsKey(ARG_TWO_PANE)) {
                twoPane = it.getBoolean(ARG_TWO_PANE)
            }
        }
    }

    /**
     * After onCreate.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_item_detail, container, false)

        val appCompatActivity = (activity as AppCompatActivity).apply {
            setSupportActionBar(rootView.detail_toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(!twoPane)
        }

        with(rootView) {
            item?.let {
                item_detail_container.item_detail.text = BGMovieDescriptor(item).toString()

                with(detail_app_bar) {

                    val bg = ContextCompat.getDrawable(
                        appCompatActivity.applicationContext,
                        R.drawable.forest
                    )?.apply {
                        layoutParams.height = intrinsicHeight
                    }

                    with (detail_toolbar_layout) {
                        title = item?.title ?: "NULL"
                        background = bg
                    }

                }
            }
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Play go go!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        const val ARG_TWO_PANE = "is_two_pane"
    }
}
