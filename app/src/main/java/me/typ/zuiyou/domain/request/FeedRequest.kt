package me.typ.zuiyou.domain.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.domain.request.BaseRequest
import me.typ.zuiyou.data.model.Feed
import me.typ.zuiyou.data.repository.FeedRepository

class FeedRequest : BaseRequest() {

    private val mFeedList = MutableLiveData<DataResult<List<Feed>>>()

    fun getFeedList(): LiveData<DataResult<List<Feed>>> {
        return mFeedList
    }

    fun requestFeedList() {
        FeedRepository.INSTANCE.getFeedList { mFeedList.value = it }
    }
}