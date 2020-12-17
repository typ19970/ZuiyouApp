package me.typ.zuiyou.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import me.typ.zuiyou.data.model.Feed

object DiffUtils {

    private var mFeedListItemCallback: DiffUtil.ItemCallback<Feed>? = null


    fun getFeedListItemCallback(): DiffUtil.ItemCallback<Feed> {
        if (mFeedListItemCallback == null) {
            mFeedListItemCallback = object : DiffUtil.ItemCallback<Feed>() {
                override fun areItemsTheSame(oldItem: Feed, newItem: Feed) = oldItem == newItem

                override fun areContentsTheSame(oldItem: Feed, newItem: Feed) =
                    oldItem.itemId == newItem.itemId
            }
        }
        return mFeedListItemCallback!!
    }

}