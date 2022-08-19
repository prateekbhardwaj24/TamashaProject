package com.example.androidcomponents.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

 object Dimension {
    fun convertSpToPixels(sp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            context.resources.displayMetrics
        ).toInt()
    }

    fun convertDpToPixels(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }
     fun getDisplaySize(context: Context): DisplayMetrics {
         return context.resources.displayMetrics
     }
}