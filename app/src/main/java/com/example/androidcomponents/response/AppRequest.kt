package com.example.androidcomponents.response


import com.android.volley.Response
import org.json.JSONObject

class AppRequest: CustomJsonRequest {

    constructor(
        method: Int,
        url: String?,
        listener: Response.Listener<JSONObject?>?,
        errorListener: Response.ErrorListener?) : super(method,url, listener, errorListener) {
    }
}