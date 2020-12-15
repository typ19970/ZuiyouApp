package me.typ.zuiyou.data.repository

import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.data.response.ResponseStatus
import me.typ.zuiyou.data.model.SampleModel

class DataRepository private constructor() {

    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DataRepository() }
    }

    fun getData(result: DataResult.Result<List<SampleModel>>) {

        val mockData = listOf(
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel(""),
            SampleModel("")
        )
        result.onResult(DataResult(mockData, ResponseStatus()))
    }
}