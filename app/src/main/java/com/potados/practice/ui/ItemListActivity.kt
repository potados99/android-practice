package com.potados.practice.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.view.View
import com.potados.practice.data.movie.BGMovieProvider
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel

import kotlinx.android.synthetic.main.activity_bgmovie_list.*
import kotlinx.android.synthetic.main.bgmovie_list.*
import org.koin.android.ext.android.inject
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.potados.practice.R
import com.potados.practice.data.storage.ExternalStorageProvider
import java.io.File


class ItemListActivity : AppCompatActivity() {

    private val movieProvider: BGMovieProvider by inject()
    private val storageProvider: ExternalStorageProvider by inject()
    private val vm: ItemDetailFragmentViewModel by inject() /* to share global.. no better idea.. :( */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bgmovie_list)
        setSupportActionBar(toolbar)

        gimmePermission(Manifest.permission.READ_EXTERNAL_STORAGE,
            "외장 메모리를 사용하기 위해 읽기 권한이 필요합니다.")
        gimmePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            "외장 메모리를 사용하기 위해 쓰기 권한이 필요합니다.")

        toolbar.title = this.title

        val twoPane: Boolean = (detail_fragment_container != null).apply {
            if (!this) {
                // Small screen.
                info_fab.setOnClickListener { view ->
                    Snackbar.make(view, "Help?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            else {
                // Wide screen.
                info_fab.visibility = View.INVISIBLE
            }
        }
        with(vm) {
            setTwoPane(twoPane)
        }

        setupRecyclerView(item_list, twoPane)
        setToolbarBackground("toolbar.png")
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, twoPane: Boolean) {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                LinearLayoutManager(this).orientation
            ))
        recyclerView.adapter =
            BGMovieItemRecyclerViewAdapter(this, movieProvider.moviesInList, twoPane)
    }

    private fun gimmePermission(permission: String, explanation: String) {

        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                Toast.makeText(this, explanation, Toast.LENGTH_SHORT).show()
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            }
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(permission), 9)
            }
        }
    }

    private fun setToolbarBackground(fileName: String) {
        val imgPath = storageProvider.theOnlyOneAndGoodVolumePath +
                File.separator +
                "res" +
                File.separator +
                fileName

        if (File(imgPath).exists()) {
            Drawable.createFromPath(imgPath)?.let {
                list_app_bar.background = it
            }
        }
    }
}
