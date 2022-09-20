package com.example.androidcomponents.task

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import com.example.androidcomponents.response.AppRequest
import com.example.androidcomponents.response.EmployeeResponse
import com.example.androidcomponents.utils.Links
import org.json.JSONObject


class EmployeeTask(private val context: Context) : Response.ErrorListener,
    Response.Listener<JSONObject> {
    private var liveData: MutableLiveData<Any>? = null

    @Throws(Exception::class)
    fun call(): MutableLiveData<Any>? {
        liveData = MutableLiveData<Any>()
        val url = Links.EMPLOYEE_DATA_API
        val request = AppRequest(Request.Method.GET, url, this, this)
        Volley.newRequestQueue(context).add(request)
        Log.d("Url: ", " $url ")

        return liveData
    }

    override fun onErrorResponse(error: VolleyError?) {
        liveData?.postValue(EmployeeResponse(error))
        Log.d("VolleyError: ", "-> Data not fetched due to ${error?.localizedMessage} ")
    }

    override fun onResponse(response: JSONObject?) {
        Log.d("VolleySuccess: ", "-> Data fetched successfully $response ")
        liveData?.postValue(EmployeeResponse(response))
    }

}