package com.example.androidcomponents.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.androidcomponents.task.ListingTask

class FoodListViewModel(private val context: Context) : ViewModel() {
    private lateinit var listingTask: ListingTask

    fun getListings(): MutableLiveData<Any>? {
        listingTask = ListingTask(context)
        try {
            return listingTask.call()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}