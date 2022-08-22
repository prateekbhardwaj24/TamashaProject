package com.example.androidcomponents.ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcomponents.database.model.FoodGridListModel
import com.example.androidcomponents.database.model.FoodImageListModel
import com.example.androidcomponents.database.model.FoodListModel
import com.example.androidcomponents.databinding.RvImageListingBinding
import com.example.androidcomponents.databinding.RvItemFoodListBinding
import com.example.androidcomponents.databinding.RvItemXLayoutBinding
import com.example.androidcomponents.utils.Dimension
import com.example.androidcomponents.views.SpacesItemDecoration


class FoodListAdapter(private var list: MutableList<FoodListModel>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var horiZontalList: MutableList<FoodGridListModel> = ArrayList()
    private var imageList: MutableList<FoodImageListModel> = ArrayList()
    private var listener: OnClickListener? = null

    companion object {
        const val GRID = 1
        const val HORIZONTAL_SLIDER = 2
        const val VERTICAL_SLIDER = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (list!![position].foodLayout) {
            "grid" -> GRID
            "x_slider" -> HORIZONTAL_SLIDER
            else -> VERTICAL_SLIDER
        }
    }

    fun updateList(
        list: List<FoodListModel>,
        horizontalList: MutableList<FoodGridListModel>,
        imageList: MutableList<FoodImageListModel>,
    ) {
        if (this.list == null)
            this.list = ArrayList()

        val initialPosition = this.list!!.size
        this.list?.addAll(list)
        this.horiZontalList.addAll(horizontalList)
        this.imageList.addAll(imageList)
        notifyItemRangeChanged(initialPosition, initialPosition + list.size)
    }

    class MyVerticalViewHolder(var binding: RvItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodListModel, listener: OnClickListener?) {

            binding.foodListBinding = data
            binding.apply {
                foodImage.layoutParams.height = Dimension.convertDpToPixels(190f, root.context)
                if (data.foodExtendedOffer.isEmpty()) {
                    foodExtendedOffer.visibility = View.GONE
                    seperator.visibility = View.GONE
                } else {
                    foodExtendedOffer.visibility = View.VISIBLE
                    seperator.visibility = View.VISIBLE

                }
                Glide.with(root.context).asBitmap().load(data.foodImage).dontAnimate()
                    .into(foodImage)
            }
            itemView.setOnClickListener {
                listener?.onItemClickedWithImage(
                    data.foodName,
                    (binding.foodImage.drawable as BitmapDrawable).bitmap
                )

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HORIZONTAL_SLIDER -> MyHorizontalViewHolder(
                RvImageListingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            GRID -> MyGridViewHolder(
                RvItemXLayoutBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> MyVerticalViewHolder(
                RvItemFoodListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    class MyHorizontalViewHolder(var binding: RvImageListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var adapter: SubAdapter
        fun bind(
            data: MutableList<FoodImageListModel>,
            listener: OnClickListener?
        ) {
            if (data.size <= 0) {
                binding.imageList.visibility = View.GONE
            } else {
                val decoration = SpacesItemDecoration(
                    Dimension.convertDpToPixels(8f, binding.root.context),
                    LinearLayoutManager.HORIZONTAL,
                    true,
                    true
                )
                if (binding.imageList.itemDecorationCount > 0)
                    binding.imageList.removeItemDecorationAt(0)

                binding.imageList.addItemDecoration(
                    decoration
                )
                binding.imageList.layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SubAdapter(data, itemViewType, binding.root.context)
                binding.imageList.adapter = adapter
                if (listener != null) {
                    adapter.setCLickListener(listener)
                }
            }
        }
    }

    class MyGridViewHolder(var binding: RvItemXLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var adapter: SubAdapter
        private val SPANCOUNT = 2
        fun bind(
            data: MutableList<FoodGridListModel>,
            listener: OnClickListener?
        ) {

            if (data.size <= 0) {
                binding.horiList.visibility = View.GONE
            } else {
                if (binding.horiList.itemDecorationCount > 0)
                    binding.horiList.removeItemDecorationAt(0)

                binding.horiList.layoutManager = GridLayoutManager(binding.root.context, SPANCOUNT)
                val decoration = SpacesItemDecoration(
                    Dimension.convertDpToPixels(8f, binding.root.context),
                    SpacesItemDecoration.GRID,
                    true
                )
                binding.horiList.addItemDecoration(decoration)
                adapter = SubAdapter(data, itemViewType, binding.root.context)
                binding.horiList.adapter = adapter
                if (listener != null) {
                    adapter.setCLickListener(listener)
                }

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyHorizontalViewHolder -> holder.bind(imageList, listener)
            is MyVerticalViewHolder -> {
                holder.bind(list?.get(position)!!, listener)

            }
            is MyGridViewHolder -> holder.bind(horiZontalList, listener)
        }

    }

    fun setCLickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    interface OnClickListener :
        SubAdapter.OnClickListener {
        override fun onItemClicked(name: String?)
        override fun onItemClickedWithImage(foodName: String, bitmap: Bitmap?)
    }
}