package com.example.androidcomponents.response

import com.android.volley.*
import org.json.JSONObject

abstract class BaseResponse {

    var response: JSONObject? = null
        private set
    var error: VolleyError? = null

    @JvmOverloads
    constructor(response: JSONObject?) {
        this.response = response
    }

    @JvmOverloads
    constructor(volleyError: VolleyError?) {
        this.error = volleyError
    }


    @JvmName("getResponse1")
    fun getResponse(): JSONObject? {
        return response
    }


    @JvmName("getError1")
    fun getError(): VolleyError? {
        return error
    }

}

