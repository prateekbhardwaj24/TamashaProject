package com.example.androidcomponents.response


import com.android.volley.Response
import com.example.androidcomponents.task.EmployeeTask

class AppRequest: CustomJsonRequest {

    constructor(
        method: Int,
        url: String?,
        listener: EmployeeTask,
        errorListener: Response.ErrorListener?) : super(method,url, listener, errorListener) {
    }
}