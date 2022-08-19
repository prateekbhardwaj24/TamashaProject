package com.example.androidcomponents.database.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcomponents.viewmodel.FoodListViewModel


class MainViewModelFactory(private val context: Context):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
            return FoodListViewModel(context) as T
        }
        throw IllegalArgumentException("Undefined ViewModel class")
    }
}