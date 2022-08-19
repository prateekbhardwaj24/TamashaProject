package com.example.androidcomponents.response

import com.android.volley.*
import org.json.JSONObject

abstract class BaseResponse {

    var response: JSONObject? = null
        private set

    @JvmOverloads
    constructor(response: JSONObject?) {
        this.response = response
    }

    @JvmOverloads
    constructor(volleyError: VolleyError?) {

    }

     @JvmName("getResponse1")
     fun getResponse(): JSONObject? {
        return response
    }

}

