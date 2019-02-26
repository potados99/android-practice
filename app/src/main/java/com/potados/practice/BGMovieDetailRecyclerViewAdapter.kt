package com.potados.practice

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.potados.practice.data.BGMovie
import kotlinx.android.synthetic.main.bgmovie_detail_list_content.view.*
import kotlinx.android.synthetic.main.bgmovie_list_content.view.*
import java.util.*

class BGMovieDetailRecyclerViewAdapter(private val source: Map<String, String>)
    : RecyclerView.Adapter<BGMovieDetailRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bgmovie_detail_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = source.entries.toList()[position]

        with(holder) {
            keyView.text = item.key
            valueView.text = item.value
        }
    }

    override fun getItemCount(): Int = source.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keyView: TextView = view.detail_key
        val valueView: TextView = view.detail_value
    }
}