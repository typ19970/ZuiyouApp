package me.typ.zuiyou.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter
import me.typ.zuiyou.R
import me.typ.zuiyou.data.model.Feed
import me.typ.zuiyou.databinding.LayoutFeedListItemBinding

class FeedListAdapter(context: Context) : SimpleDataBindingAdapter<Feed, LayoutFeedListItemBinding>(
    context,
    R.layout.layout_feed_list_item,
    DiffUtils.getFeedListItemCallback()
) {
    override fun onBindItem(
        binding: LayoutFeedListItemBinding?,
        item: Feed?,
        holder: RecyclerView.ViewHolder?
    ) {
        binding?.feed = item
    }
}