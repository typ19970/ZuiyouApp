package me.typ.zuiyou.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter
import me.typ.zuiyou.R
import me.typ.zuiyou.data.model.SampleModel
import me.typ.zuiyou.databinding.ItemSimpleBinding

val sampleItemCallback = object : DiffUtil.ItemCallback<SampleModel>() {
    override fun areItemsTheSame(oldItem: SampleModel, newItem: SampleModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SampleModel, newItem: SampleModel): Boolean {
        return oldItem.string == newItem.string
    }
}

class SampleAdapter(context: Context) : SimpleDataBindingAdapter<SampleModel, ItemSimpleBinding>(
    context, R.layout.item_simple, sampleItemCallback
) {

    init {
        setOnItemClickListener { _, _ -> Log.e("SampleAdapter", "--------onItemClick") }
    }

    override fun onBindItem(
        binding: ItemSimpleBinding,
        item: SampleModel,
        holder: RecyclerView.ViewHolder
    ) {
        val position = holder.layoutPosition
        binding.tvSimple.text = position.toString()
        when (position % 2) {
            0 -> binding.root.setBackgroundColor(Color.RED)
            1 -> binding.root.setBackgroundColor(Color.YELLOW)
        }
    }


}