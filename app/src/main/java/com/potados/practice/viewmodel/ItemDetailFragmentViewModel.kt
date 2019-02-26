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

    private val itemId = MutableLiveData<Int>()
    fun setItemId(id: Int) {
        itemId.value = id
        getDescriptor() /* refresh */
    }
    fun getItemId(): LiveData<Int> = itemId

    private val twoPane = MutableLiveData<Boolean>()
    fun setTwoPane(tp: Boolean) { twoPane.value = tp }
    fun getTwoPane(): MutableLiveData<Boolean> = twoPane

    private val descriptor = MutableLiveData<BGMovieDescriptor>()
    fun getDescriptor(): MutableLiveData<BGMovieDescriptor> =
        descriptor.apply{
            value = BGMovieDescriptor(provider.getMovieById(itemId.value)).assertNonNull()
        }

    override fun onCleared() {
        super.onCleared()
    }
}