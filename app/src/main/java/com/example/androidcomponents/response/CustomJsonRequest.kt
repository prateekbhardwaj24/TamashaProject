package com.example.androidcomponents.response

import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.example.androidcomponents.task.ListingTask
import org.json.JSONObject

open class CustomJsonRequest(
    method: Int,
    url: String?,
    listener: Response.Listener<JSONObject?>?,
    errorListener: Response.ErrorListener?
) : JsonObjectRequest(method,url.toString(),null, listener, errorListener) {


    override fun deliverResponse(response: JSONObject?) {
        super.deliverResponse(response)
    }

    override fun deliverError(error: VolleyError?) {
        super.deliverError(error)
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<JSONObject?>? {
        return super.parseNetworkResponse(response)
    }

    override fun parseNetworkError(volleyError: VolleyError): VolleyError? {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            return AuthFailureError(String(volleyError.networkResponse.data))
        }
        return volleyError
    }

}