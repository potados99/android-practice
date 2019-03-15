package com.potados.practice.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potados.practice.R
import com.potados.practice.util.Alert
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
                val desc = vm.getDescriptor().value
                val file =  desc?.playableFile

                if (desc == null)
                    Alert.toast("뷰 모델에 문제가 있습니다.")
                else if (file == null)
                    Alert.toast("영상을 재생할 수 없습니다.")

                else if (!desc.makePlayable(true))
                    Alert.toast("영상을 처리하는 데 실패했습니다.")
                else {
                    val uri = Uri.parse(file.absolutePath)
                    val playIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                        setDataAndType(uri, "video/mp4")
                    }
                    startActivity(playIntent)
                }
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
}
