package com.example.androidcomponents.response

import android.util.Log
import com.android.volley.*
import com.example.androidcomponents.database.model.Model.EmployeeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class EmployeeResponse : BaseResponse {
    private var employeeList: MutableList<EmployeeData>? = null
    private var apiError: String? = null
    private var status: String? = null
    private var message: String? = null

    constructor(response: JSONObject?) : super(response) {
        getData()
    }

    constructor(error: VolleyError?) : super(error) {
        getErrorData()
    }

    private fun getErrorData() {
        if (getError() is TimeoutError || getError() is NoConnectionError) {
            apiError = "Time out or may be connection off"
        } else if (getError() is AuthFailureError) {
            apiError = "authentication failure!!"
        } else if (getError() is ServerError) {
            apiError = "No response from server"
        } else if (getError() is NetworkError) {
            apiError = "Network error"
        } else if (getError() is ParseError) {
            apiError = "Data parsing error "
        } else {
            apiError = "UnKnown error!!"
        }
    }

    private fun getData() {
        try {
            status = getResponse()?.getString("status")
            message = getResponse()?.getString("message")
            val itemArray = getResponse()?.getJSONArray("data")

            employeeList = Gson().fromJson<ArrayList<EmployeeData>>(
                itemArray.toString(),
                object : TypeToken<ArrayList<EmployeeData>?>() {}.type
            )
        } catch (e: Exception) {
            Log.d("Error: ", "${e.message}")
            e.printStackTrace()
        }
    }

    fun getEmployeeList(): MutableList<EmployeeData> {
        if (employeeList == null)
            employeeList = ArrayList()
        return employeeList!!
    }

    fun getStatus(): String {
        if (status == null)
            status = ""

        return status!!
    }

    fun getMessage(): String {
        if (message == null)
            message = "No message found"

        return message!!
    }

    fun getErrorMessage(): String? {
        if (apiError == null)
            apiError = "Please try again"
        return apiError
    }
}