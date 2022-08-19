package com.example.androidcomponents.response

import com.android.volley.VolleyError
import com.example.androidcomponents.database.model.FoodGridListModel
import com.example.androidcomponents.database.model.FoodImageListModel
import com.example.androidcomponents.database.model.FoodListModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class ListingResponse : BaseResponse {
    private var exploreList: MutableList<FoodListModel>? = null
    private var exploreHorizontalList: MutableList<FoodGridListModel>? = null
    private var exploreImageList: MutableList<FoodImageListModel>? = null

    constructor(response: JSONObject?) : super(response) {
        getData()
    }

    constructor(error: VolleyError?) : super(error)

    private fun getData() {
        try {
            val itemArray = getResponse()?.optJSONObject("data")?.optJSONArray("verticalList")
            val itemArray2 = getResponse()?.optJSONObject("data")?.optJSONArray("horiList")
            val itemArray3 = getResponse()?.optJSONObject("data")?.optJSONArray("imageList")
            exploreList = Gson().fromJson<ArrayList<FoodListModel>>(
                itemArray.toString(),
                object : TypeToken<ArrayList<FoodListModel>?>() {}.type
            )
            exploreHorizontalList = Gson().fromJson<ArrayList<FoodGridListModel>>(
                itemArray2.toString(),
                object : TypeToken<ArrayList<FoodGridListModel>?>() {}.type
            )
            exploreImageList = Gson().fromJson<ArrayList<FoodImageListModel>>(
                itemArray3.toString(),
                object : TypeToken<ArrayList<FoodImageListModel>?>() {}.type
            )


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getHorizontalList(): MutableList<FoodGridListModel> {
        if (exploreHorizontalList == null)
            exploreHorizontalList = ArrayList()

        return exploreHorizontalList!!
    }

    fun getImageList(): MutableList<FoodImageListModel> {
        if (exploreImageList == null)
            exploreImageList = ArrayList()

        return exploreImageList!!
    }

    fun getList(): MutableList<FoodListModel> {
        if (exploreList == null)
            exploreList = ArrayList()

        return exploreList!!
    }

}