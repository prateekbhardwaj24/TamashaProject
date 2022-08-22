package com.example.androidcomponents.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcomponents.database.model.FoodGridListModel
import com.example.androidcomponents.database.model.FoodImageListModel

import com.example.androidcomponents.database.model.FoodListModel
import com.example.androidcomponents.databinding.RvItemFoodListBinding
import com.example.androidcomponents.databinding.RvItemGridViewBinding
import com.example.androidcomponents.databinding.RvItemImageListBinding
import com.example.androidcomponents.utils.Dimension

class SubAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listener: OnClickListener? = null
    private val horizontalList: ArrayList<FoodImageListModel> by lazy { ArrayList<FoodImageListModel>() }
    private val verticalList: ArrayList<FoodListModel> by lazy { ArrayList<FoodListModel>() }
    private val gridList: ArrayList<FoodGridListModel> by lazy { ArrayList<FoodGridListModel>() }
    private val grid: Int = 1
    private val slider: Int = 2
    private val list: Int = 3
    val GRID = 1
    val HORIZONTAL_SLIDER = 2
    val VERTICAL_SLIDER = 3
    private var layoutType: Int = 1

    constructor(list: MutableList<*>, layoutType: Int, context: Context) : this() {
        when (layoutType) {
            HORIZONTAL_SLIDER -> horizontalList.addAll(list as ArrayList<FoodImageListModel>)
            GRID -> gridList.addAll(list as ArrayList<FoodGridListModel>)
            VERTICAL_SLIDER -> verticalList.addAll(list as ArrayList<FoodListModel>)
        }
        this.layoutType = layoutType
    }

    override fun getItemViewType(position: Int): Int {
        return when (layoutType) {
            HORIZONTAL_SLIDER -> slider
            GRID -> grid
            else -> list
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            grid ->
                GridViewHolder(
                    RvItemGridViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            slider ->
                SliderViewHolder(
                    RvItemImageListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else ->
                ListViewHolder(
                    RvItemFoodListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }
    }

    class ListViewHolder(var binding: RvItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodListModel, layoutType: Int) {

            binding.foodImage.layoutParams.height = Dimension.convertDpToPixels(190f, binding.root.context)
            binding.foodDesc.text = data.foodDesc
            binding.foodPrice.text = data.foodPrice
            binding.foodRating.text = data.foodRating
            binding.foodName.text = data.foodName
            if (data.foodExtendedOffer.isEmpty()) {
                binding.foodExtendedOffer.visibility = View.GONE
                binding.seperator.visibility = View.GONE
            } else {
                binding.foodExtendedOffer.visibility = View.VISIBLE
                binding.seperator.visibility = View.VISIBLE
                binding.foodExtendedOffer.text = data.foodExtendedOffer
            }

            binding.foodOffer.text =
                HtmlCompat.fromHtml(data.foodOffer, HtmlCompat.FROM_HTML_MODE_COMPACT)
            Glide.with(binding.root.context).asBitmap().load(data.foodImage).into(binding.foodImage)
        }
    }

    class GridViewHolder(val binding: RvItemGridViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodGridListModel, listener: OnClickListener?) {
            //  val width = (Dimension.getDisplaySize(context).widthPixels * .16).toInt()
            val height = (Dimension.getDisplaySize(binding.root.context).heightPixels * .20).toInt()

            //  binding.foodImage.layoutParams.width = width
            binding.foodImage.layoutParams.height = height
            binding.foodImage.scaleType = ImageView.ScaleType.CENTER_CROP
            binding.foodName.text = data.foodName
            Glide.with(binding.root.context).asBitmap().load(data.foodImage).into(binding.foodImage)

            itemView.setOnClickListener {
            if (listener != null)
                listener!!.onItemClickedWithImage(data.foodName,(binding.foodImage.drawable as BitmapDrawable?)?.bitmap)
            }

        }

    }

    class SliderViewHolder(var binding: RvItemImageListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodImageListModel, listener: OnClickListener?) {
            val width = (Dimension.getDisplaySize(binding.root.context).widthPixels * .20).toInt()
            val height = (Dimension.getDisplaySize(binding.root.context).heightPixels * .10).toInt()

            binding.image.layoutParams.width = width
            binding.image.layoutParams.height = height
            Glide.with(binding.root.context).asBitmap().load(data.foodImage).into(binding.image)
            binding.name.text = data.foodName

            itemView.setOnClickListener {
                if (listener != null)
                    listener!!.onItemClickedWithImage(data.foodName,(binding.image.drawable as BitmapDrawable?)?.bitmap)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GridViewHolder -> {
                holder.bind(gridList[position],listener)

            }
            is SliderViewHolder -> {
                holder.bind(horizontalList[position],listener)

            }
           // is ListViewHolder -> holder.bind(verticalList[position], layoutType)
        }
    }

    override fun getItemCount(): Int {
        return when (layoutType) {
            HORIZONTAL_SLIDER -> horizontalList.size
            GRID -> gridList.size
            else -> verticalList.size
        }

    }

    fun setCLickListener(listener: OnClickListener?) {
        this.listener = listener
    }

    interface OnClickListener {
        fun onItemClicked(name: String?)
        fun onItemClickedWithImage(foodName: String, bitmap: Bitmap?)
    }
}