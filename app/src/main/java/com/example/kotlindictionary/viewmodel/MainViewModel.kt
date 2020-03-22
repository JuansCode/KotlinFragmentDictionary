package com.example.kotlindictionary.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.kotlindictionary.model.ListItem
import com.example.kotlindictionary.model.UrbanResponse
import com.example.kotlindictionary.network.ApiClient
import com.example.kotlindictionary.util.RxSingleSchedulers
import com.example.kotlindictionary.viewmodel.adapter.UrbanAdapter
import io.reactivex.disposables.CompositeDisposable


class MainViewModel: CustomViewModel() {

    private var disposable: CompositeDisposable? = CompositeDisposable()
    val data: ArrayList<ListItem> = ArrayList()
    private var rxSingleSchedulers: RxSingleSchedulers = RxSingleSchedulers.DEFAULT
    private var apiService: ApiClient = ApiClient()
    val adapter: UrbanAdapter = UrbanAdapter(data)

    var clicked : Boolean = false


    @SuppressLint("CheckResult")
    fun loadData( term : String) {
        onLoading()
//        data.clear()
        disposable?.add(
            apiService.getDefinitionList(term)
                .compose(rxSingleSchedulers.applySchedulers())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(urbanModel: UrbanResponse) {
        data.clear()
        urbanModel.list?.forEach { items ->
            if (items != null) {
                data.add(items)
            }
        }
        adapter.notifyDataSetChanged()
        dataLoading.value = false
    }

    private fun onError(error: Throwable) {
        errorMessage = error.message ?: ""
        dataLoading.value = false
    }

    private fun onLoading() {
        dataLoading.value = true
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.run {
            clear()
            null
        }
    }

    fun sortList(){
        if(!clicked){
            clicked = true
            adapter.updateData(
                data.sortedByDescending { it.thumbs_up }
            )
        }else{
            clicked = false
            adapter.updateData(
                data.sortedByDescending { it.thumbs_down }
            )
        }
    }

}