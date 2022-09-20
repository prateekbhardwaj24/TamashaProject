package com.example.androidcomponents.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.VolleyError
import com.example.androidcomponents.R
import com.example.androidcomponents.databinding.ActivityEmployeListActivtyBinding
import com.example.androidcomponents.response.EmployeeResponse
import com.example.androidcomponents.ui.adapter.EmployeAdapter
import com.example.androidcomponents.utils.Dimension
import com.example.androidcomponents.viewmodel.EmployeViewModel
import com.example.androidcomponents.views.SpacesItemDecoration

class EmployeListActivty : AppCompatActivity(), Observer<Any> {
    private var mBinding: ActivityEmployeListActivtyBinding? = null
    private lateinit var viewModel: EmployeViewModel
    private var mdapter: EmployeAdapter? = null

    override fun onStart() {
        super.onStart()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EmployeViewModel::class.java]
        viewModel.getListings(this)?.observe(this, this)
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employe_list_activty
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
        mdapter = EmployeAdapter(null)

        mBinding?.listRv?.adapter = mdapter
    }

    override fun onPause() {
        super.onPause()
        mBinding?.shimmerViewContainer?.stopShimmerAnimation()
        mBinding?.shimmerViewContainer?.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    override fun onChanged(t: Any?) {
        mBinding?.shimmerViewContainer?.stopShimmerAnimation()
        mBinding?.shimmerViewContainer?.visibility = View.GONE
        if (t != null && t is EmployeeResponse) {
            if (t.getStatus() == "success" && t.getEmployeeList().size > 0) {
                mBinding?.listRv?.visibility = View.VISIBLE
                mdapter?.updateList(t.getEmployeeList())
            } else {
                mBinding?.errorView?.visibility = View.VISIBLE
                mBinding?.statusAndMessage?.text =
                    "Status: ${t.getStatus()} \n Message: ${t.getMessage()} \n VolleyError: ${t.getErrorMessage()}"
            }
            Log.d("VolleyData: ", "status: ${t.getStatus()} \n message: ${t.getMessage()}")
        } else if (t is VolleyError) {
            Log.d("VolleyError: ", " ${t.localizedMessage}")

        }

    }
}