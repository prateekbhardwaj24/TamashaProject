package com.example.androidcomponents.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.example.androidcomponents.database.model.MainViewModelFactory
import com.example.androidcomponents.databinding.ActivityFoodListBinding
import com.example.androidcomponents.response.ListingResponse
import com.example.androidcomponents.ui.adapter.FoodListAdapter
import com.example.androidcomponents.utils.Dimension
import com.example.androidcomponents.utils.Notifications
import com.example.androidcomponents.viewmodel.FoodListViewModel
import com.example.androidcomponents.views.SpacesItemDecoration

class FoodListActivity : AppCompatActivity(), Observer<Any>, FoodListAdapter.OnClickListener {
    private var mBinding: ActivityFoodListBinding? = null
    private lateinit var viewModel: FoodListViewModel
    private lateinit var factory: MainViewModelFactory
    private  var mdapter: FoodListAdapter? = null
    private lateinit var testLive: MutableLiveData<String>
   // private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onStart() {
        super.onStart()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factory = MainViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory)[FoodListViewModel::class.java]
        viewModel.getListings()?.observe(this, this)
        mBinding = DataBindingUtil.setContentView(
            this,
            com.example.androidcomponents.R.layout.activity_food_list
        )
        initView()

    }

    private fun initView() {
        mBinding?.shimmerViewContainer?.startShimmerAnimation()
        val mLayoutManager = LinearLayoutManager(this)
        mBinding?.listRv?.layoutManager = mLayoutManager
        if (mBinding?.listRv?.itemDecorationCount!! > 0)
            mBinding?.listRv?.removeItemDecorationAt(0)

        val eightDp = Dimension.convertDpToPixels(8f, mBinding?.root!!.context)
        val decoration = SpacesItemDecoration(
            eightDp,
            LinearLayoutManager.VERTICAL, true, true
        )
        mBinding?.listRv?.addItemDecoration(decoration)
        mBinding?.listRv?.setHasFixedSize(false)
        mdapter = FoodListAdapter(null)
        mdapter?.setCLickListener(this)
        mBinding?.listRv?.adapter = mdapter
    }

    override fun onPause() {
        super.onPause()
        mBinding?.shimmerViewContainer?.stopShimmerAnimation()
    }

    override fun onChanged(t: Any?) {
        mBinding?.shimmerViewContainer?.stopShimmerAnimation()
        if (t is ListingResponse) {
            mBinding?.shimmerViewContainer?.visibility = View.GONE
            mdapter?.updateList(t.getList(), t.getHorizontalList(), t.getImageList())

        } else if (t is VolleyError) {
            Log.d("shaker1: ", "-> error = ${t.localizedMessage}}")
        }
    }

    override fun onItemClicked(name: String?) {
        Toast.makeText(this, "clicked-> $name", Toast.LENGTH_LONG).show()
//        var noti = Notifications(this)
//        noti.createNotification()
//        noti.addNotification(null, null)
    }

    override fun onItemClickedWithImage(foodName: String, bitmap: Bitmap?) {
        Toast.makeText(this, "clicked-> $foodName", Toast.LENGTH_LONG).show()
        var noti = Notifications(this)
        noti.addNotification(foodName,bitmap)
    }
}