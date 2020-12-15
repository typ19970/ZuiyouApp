package me.typ.zuiyou.domain.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.domain.request.BaseRequest
import me.typ.zuiyou.data.model.SampleModel
import me.typ.zuiyou.data.repository.DataRepository

class SampleRequest : BaseRequest() {

    private val liveData = MutableLiveData<List<SampleModel>>()


    fun getData(): LiveData<List<SampleModel>> {
        return liveData
    }

    fun requestData() {
        DataRepository.INSTANCE.getData{
            liveData.value = it.result
        }
    }

}