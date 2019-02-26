package com.potados.practice

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.fragment_bgmovie_detail.*

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    companion object {
        var running: Boolean = false
    }

    override fun onStart() {
        super.onStart()

        running = true
    }

    override fun onStop() {
        super.onStop()

        running = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // First time being loaded.
            val id = intent.getIntExtra(ItemDetailFragment.ARG_ITEM_ID, 0)
            val tp = intent.getBooleanExtra(ItemDetailFragment.ARG_TWO_PANE, false)

            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ItemDetailFragment.ARG_ITEM_ID, id)
                    putBoolean(ItemDetailFragment.ARG_TWO_PANE, tp)
                }
            }

            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment) /* setting content view */
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // Up(back) button
                navigateUpTo(Intent(this, ItemListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
