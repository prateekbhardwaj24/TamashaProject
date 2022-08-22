package com.example.androidcomponents.task

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.*
import com.android.volley.toolbox.Volley
import com.example.androidcomponents.response.AppRequest
import com.example.androidcomponents.response.ListingResponse
import com.example.androidcomponents.utils.Links.DEFAULT_LIST_API
import org.json.JSONObject

class ListingTask(private val context: Context) : Response.Listener<JSONObject?>,
    Response.ErrorListener {
    private var mMediatorLiveData: MutableLiveData<Any>? = null

    @Throws(Exception::class)
    fun call(): MutableLiveData<Any>? {
        mMediatorLiveData = MutableLiveData<Any>()
        val url = DEFAULT_LIST_API
        Log.d("shaker1: ", "-> url= $url")
        val request = AppRequest(Request.Method.GET, url, this, this)
        Volley.newRequestQueue(context.applicationContext).add(request)
        return mMediatorLiveData
    }


    @Override
    override fun onErrorResponse(error: VolleyError?) {
        mMediatorLiveData!!.postValue(ListingResponse(error))
    }

    @Override
    override fun onResponse(response: JSONObject?) {
        mMediatorLiveData!!.postValue(ListingResponse(response))
    }
}