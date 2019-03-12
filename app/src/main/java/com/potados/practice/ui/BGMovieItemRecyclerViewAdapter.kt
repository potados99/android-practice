package com.potados.practice.ui

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.potados.practice.R
import com.potados.practice.data.movie.BGMovie
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel
import kotlinx.android.synthetic.main.bgmovie_list_content.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class BGMovieItemRecyclerViewAdapter(
    private val parentActivity: ItemListActivity,
    private val source: List<BGMovie>,
    private val twoPane: Boolean
) : RecyclerView.Adapter<BGMovieItemRecyclerViewAdapter.ViewHolder>(),
    KoinComponent {

    private val vm: ItemDetailFragmentViewModel by inject() /* to share global.. no better idea.. :( */

    private val onClickListener = View.OnClickListener { view ->
        val item = view.tag as BGMovie /* selected item */

        with(vm) {
            setItemId(item.id)
        }

        if (twoPane) {
            // Update fragment.
            parentActivity.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.detail_fragment_container,
                    ItemDetailFragment()
                )
                .commit()
        }

        else {
            // Launch new activity.
            if (!ItemDetailActivity.running)
                view.context.let {
                    it.startActivity(Intent(it, ItemDetailActivity::class.java))
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.bgmovie_list_content, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = source[position]

        with(holder) {
            idView.text = item.id.toString()
            contentView.text = item.title

            itemView.tag = item
            itemView.setOnClickListener(onClickListener)

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