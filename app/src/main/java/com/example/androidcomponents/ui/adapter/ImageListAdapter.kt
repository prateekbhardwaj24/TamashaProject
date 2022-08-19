package com.example.androidcomponents.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcomponents.database.model.FoodImageListModel
import com.example.androidcomponents.database.model.FoodListModel
import com.example.androidcomponents.databinding.RvItemImageListBinding
import com.example.androidcomponents.utils.Dimension

class ImageListAdapter(
    private val list: MutableList<FoodImageListModel>,
    private val context: Context
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    private var listener: OnClickListener? = null

    class ViewHolder(var binding: RvItemImageListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodImageListModel, context: Context) {
            val width = (Dimension.getDisplaySize(context).widthPixels * .20).toInt()
            val height = (Dimension.getDisplaySize(context).heightPixels * .10).toInt()

            binding.image.layoutParams.width = width
            binding.image.layoutParams.height = height
            Glide.with(context).asBitmap().load(data.foodImage).into(binding.image)
            binding.name.text = data.foodName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemImageListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], context)
        holder.itemView.setOnClickListener {
            if (listener != null)
                listener!!.onItemClicked(list[position].foodName)
        }
    }

    override fun getItemCount(): Int {
        return list.size ?: 0
    }


    interface OnClickListener {
        fun onItemClicked(name: String?)
    }
}