package com.example.androidcomponents.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcomponents.R
import com.example.androidcomponents.database.model.Model.EmployeeData
import com.example.androidcomponents.databinding.RvEmployeItemBinding


class EmployeAdapter(private var list: MutableList<EmployeeData>?) :
    RecyclerView.Adapter<EmployeAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: RvEmployeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindViews(data: EmployeeData) {
            binding.apply {
                empId.text = "Id: ${data.id}"
                empName.text = "Name: ${data.employee_name}"
                empAge.text = "Age: ${data.employee_age}"
                empSalary.text = "Salary: ${data.employee_salary}"
                Glide.with(empProfile.context).asBitmap().load(data.profile_image)
                    .placeholder(R.drawable.profile_avatar).into(empProfile)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RvEmployeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindViews(list?.get(position)!!)

        holder.binding.salaryBtn.setOnClickListener {
            //reverse the visibility, and reverse the expand value
            setVisibility(list?.get(position)!!.expand, holder.binding)
            list?.get(position)?.expand = !list?.get(position)?.expand!!
        }

        //Handling visibility to prevent view restore
        setVisibility(!list?.get(position)!!.expand, holder.binding)
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    //Will update the recycler when there will be data
    fun updateList(
        list: List<EmployeeData>
    ) {
        if (this.list == null)
            this.list = ArrayList()

        val initialPosition = this.list!!.size
        this.list?.addAll(list)
        notifyItemRangeChanged(initialPosition, initialPosition + list.size)
    }

    private fun setVisibility(visibility: Boolean, binding: RvEmployeItemBinding) {
        if (visibility) {
            binding.bottomGroup.visibility = View.GONE
            binding.salaryBtn.text = "show salary"
        } else {
            binding.bottomGroup.visibility = View.VISIBLE
            binding.salaryBtn.text = "hide salary"
        }
    }

}