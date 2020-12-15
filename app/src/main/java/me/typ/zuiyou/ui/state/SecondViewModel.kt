package me.typ.zuiyou.ui.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.typ.zuiyou.data.model.SampleModel
import me.typ.zuiyou.domain.request.SampleRequest

class SecondViewModel : ViewModel() {

    val list = MutableLiveData<List<SampleModel>>()

    val sampleRequest = SampleRequest()

}