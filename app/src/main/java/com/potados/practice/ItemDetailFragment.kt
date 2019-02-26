package com.potados.practice

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.potados.practice.data.BGMovie
import com.potados.practice.data.BGMovieDescriptor
import com.potados.practice.data.BGMovieProvider
import com.potados.practice.model.ItemDetailFragmentModel
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel
import kotlinx.android.synthetic.main.fragment_bgmovie_detail.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment() : Fragment() {

    private val vm by viewModel<ItemDetailFragmentViewModel>()
    private val parentActivity by lazy { activity as AppCompatActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            with(vm) {
                if (it.containsKey(ARG_ITEM_ID)) {
                    setItemId(it.getInt(ARG_ITEM_ID))
                }
                if (it.containsKey(ARG_TWO_PANE)) {
                    setTwoPane(it.getBoolean(ARG_TWO_PANE))
                }
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
        val rootView = inflater.inflate(R.layout.fragment_bgmovie_detail,
            container, false).apply {
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Play go go!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            parentActivity.setSupportActionBar(detail_toolbar)
        }

        with(vm) {
            val owner = this@ItemDetailFragment

            getDescriptor().observe(owner, Observer {
                it?.let { desc ->
                    with(rootView) {
                        detail_list.adapter = BGMovieDetailRecyclerViewAdapter(desc.fieldsMap)

                        // App bar
                        with(detail_app_bar) {
                            val bg = desc.thumbNail.apply {
                                layoutParams.height = intrinsicHeight
                            }

                            with(detail_toolbar_layout) {
                                title = desc.title
                                background = bg
                            }
                        }
                    }
                }
            })

            getTwoPane().observe(owner, Observer {
                it?.let { tp ->
                    parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(!tp)
                }
            })
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

