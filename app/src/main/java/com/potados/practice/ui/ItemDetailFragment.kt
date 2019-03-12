package com.potados.practice.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potados.practice.R
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel
import kotlinx.android.synthetic.main.fragment_bgmovie_detail.view.*
import org.koin.android.ext.android.inject

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment() : Fragment() {

    private val parentActivity by lazy { activity as AppCompatActivity }
    private val vm: ItemDetailFragmentViewModel by inject() /* to share global.. no better idea.. :( */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * After onCreate.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bgmovie_detail, container, false)

        with (rootView) {
            fab.setOnClickListener { view ->
                Snackbar
                    .make(view, "Play go go!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

            parentActivity.setSupportActionBar(detail_toolbar)
        }

        with(vm) {
            val owner = this@ItemDetailFragment

            getTwoPane().observe(owner, Observer { it?.let { tp ->
                parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(!tp)
            }})

            /**
             * Both getDescriptor() and getAdapter() could be replaced with getItemId()
             * because those are dependent on the id.
             * It would be a better choice to reduce observer for performance(maybe)
             * BUT placing observer for every is more visible and intuitive.
             * Items of view model used in this fragment MUST have its own observer.
             */

            // getItemId()... is not directly used in this fragment.

            getDescriptor().observe(owner, Observer { it?.let { desc ->
                with(rootView) {
                    detail_app_bar.layoutParams.height = desc.thumbNail.intrinsicHeight
                    detail_toolbar_layout.title = desc.title
                    detail_toolbar_layout.background = desc.thumbNail
                }
            }})

            getAdapter().observe(owner, Observer { it?.let { adapter ->
                rootView.detail_list.adapter = adapter
            }})

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

