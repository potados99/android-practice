package com.potados.practice.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.potados.practice.R
import kotlinx.android.synthetic.main.bgmovie_detail_list_content.view.*

class BGMovieDetailRecyclerViewAdapter(private val source: List<Map<String, String>>)
    : RecyclerView.Adapter<BGMovieDetailRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bgmovie_detail_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = source[position]

        with(holder) {
            keyView.text = item.keys.first() /* only on pair in each map. */
            valueView.text = item.values.first()
        }
    }

    override fun getItemCount(): Int = source.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keyView: TextView = view.detail_key
        val valueView: TextView = view.detail_value
    }
}