package com.example.androidcomponents.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcomponents.task.EmployeeTask

class EmployeViewModel :ViewModel() {

    private  var employeeTask: EmployeeTask? = null

    fun getListings(context: Context): MutableLiveData<Any>? {

        if (employeeTask == null)
             employeeTask = EmployeeTask(context)

        try {
            return employeeTask?.call()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}