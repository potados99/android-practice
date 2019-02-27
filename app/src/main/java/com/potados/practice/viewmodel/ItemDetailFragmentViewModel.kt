package com.potados.practice.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.potados.practice.BGMovieDetailRecyclerViewAdapter
import com.potados.practice.data.BGMovie
import com.potados.practice.data.BGMovieDescriptor
import com.potados.practice.data.BGMovieProvider
import com.potados.practice.data.InvalidBGMovie
import org.koin.android.ext.android.inject


class ItemDetailFragmentViewModel(private val provider: BGMovieProvider) : ViewModel() {

    /**
     * Is it two-pane?
     */
    private val twoPane = MutableLiveData<Boolean>()
    fun setTwoPane(tp: Boolean) {
        twoPane.value = tp
    }
    fun getTwoPane(): LiveData<Boolean> = twoPane

    /**
     * UNDER ARE ALL itemId DECISIVE.
     * THEREFORE itemId MUST EXPLICITLY UPDATE ALL OTHER PROPERTIES USING IT.
     */

    /**
     * Selected item id
     */
    private val itemId = MutableLiveData<Int>()
    fun setItemId(id: Int) {
        itemId.value = id

        // Update changes to other properties.
        getDescriptor()
        getAdapter()
    }
    fun getItemId(): LiveData<Int> = itemId

    /**
     * Descriptor of selected item.
     * Read only.
     */
    private val descriptor = MutableLiveData<BGMovieDescriptor>()
    fun getDescriptor(): LiveData<BGMovieDescriptor> =
        descriptor.apply{
            value = BGMovieDescriptor(provider.getMovieById(itemId.value)).assertNonNull()
    }

    /**
     * Adapter for detail recycler view.
     * Read only.
     */
    private val adapter = MutableLiveData<BGMovieDetailRecyclerViewAdapter>()
    fun getAdapter(): LiveData<BGMovieDetailRecyclerViewAdapter> =
        adapter.apply {
            descriptor.value?.let { desc ->
                value = BGMovieDetailRecyclerViewAdapter(desc.fieldsMap) /* No adapter for null. */
            }
        }

    override fun onCleared() {
        super.onCleared()
    }
}