package com.potados.practice

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.potados.practice.data.BGMovie
import kotlinx.android.synthetic.main.bgmovie_list_content.view.*

class BGMovieItemRecyclerViewAdapter(
    private val parentActivity: ItemListActivity,
    private val source: List<BGMovie>,
    private val twoPane: Boolean
) : RecyclerView.Adapter<BGMovieItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener = View.OnClickListener { v ->
        val item = v.tag as BGMovie /* selected item */

        if (twoPane) {
            // Update fragment.

            val fragment = ItemDetailFragment().apply {
                /* new fragment. */
                arguments = Bundle().apply {
                    /* new bundle as an argument in the fragment. */
                    putInt(ItemDetailFragment.ARG_ITEM_ID, item.id)
                    putBoolean(ItemDetailFragment.ARG_TWO_PANE, twoPane)
                }
            }

            parentActivity.supportFragmentManager /* item_detail_container on activity_bgmovie_list. */
                .beginTransaction()
                .replace(R.id.detail_fragment_container, fragment) // correct.
                .commit()
        }

        else {
            // Launch new activity.s

            val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
            }

            v.context.startActivity(intent)
        }
    }

    /**
     * Create view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bgmovie_list_content, parent, false)
        return ViewHolder(view)
    }

    /**
     * The created holder (ViewHolder)
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = source[position]

        with(holder) {
            idView.text = item.id.toString()
            contentView.text = item.title

            with(itemView) {
                tag = item /* tag of each cell is the item (BGMovie) */
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun getItemCount() = source.size

    /**
     * This adapter is for this view holder.
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}