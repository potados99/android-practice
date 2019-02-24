package com.potados.practice

import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potados.practice.data.BGMovie
import com.potados.practice.data.BGMovieDescripter
import com.potados.practice.data.BGMovieProvider
import com.potados.practice.data.BGMovieRespsitoryImpl
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private var item: BGMovie? = null
    private val provider = BGMovieProvider(BGMovieRespsitoryImpl("hey"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // get selected item and set toolbar title.
                item = provider.getValidMap()?.get(it.getInt(ARG_ITEM_ID))
                activity?.toolbar_layout?.title = item?.title ?: "NULL"
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
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        item?.let {
            rootView.item_detail.text = BGMovieDescripter(item).getDetailsString()
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
