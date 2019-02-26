package com.potados.practice

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // First time being loaded.

            val fragment = ItemDetailFragment().apply {
                // New fragment.
                arguments = Bundle().apply {
                    // New bundle as an argument in the fragment.
                    val id = intent.getIntExtra(ItemDetailFragment.ARG_ITEM_ID, -99)
                    putInt(ItemDetailFragment.ARG_ITEM_ID, id)
                }
            }

            supportFragmentManager
                .beginTransaction() /* item_detail_container on itself(activity_item_detail). */
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
