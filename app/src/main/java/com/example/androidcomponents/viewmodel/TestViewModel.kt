package com.example.androidcomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {
    fun inceaseData1(data1: Int) {
        this.data1 = data1
    }

    var data1: Int = 0
    var data2: Int = 0

    }