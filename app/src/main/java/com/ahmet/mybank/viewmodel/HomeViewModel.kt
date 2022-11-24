package com.ahmet.mybank.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmet.mybank.api.ApiServiceInterfaceRepository
import com.ahmet.mybank.api.ApiServiceRepository
import com.ahmet.mybank.models.Bank
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ApiServiceInterfaceRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val bank = MutableLiveData<List<Bank>>()
    val banks: LiveData<List<Bank>>
        get() = bank

    private val dataLoading_ = MutableLiveData<Boolean>()
    val dataLoading:LiveData<Boolean>
    get() = dataLoading_


    private val dataError_=MutableLiveData<Boolean>()
    val dataError:LiveData<Boolean>
    get() = dataError_


    fun getData() {
        compositeDisposable.add(
            repository.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Bank>>() {
                    override fun onSuccess(t: List<Bank>) {
                        bank.value=t
                        dataLoading_.value = false
                        dataError_.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        dataLoading_.value = false
                        dataError_.value = true
                    }

                })
        )
    }

    fun getSearchData(term:String) : List<Bank> {
        val deger= bank.value?.filter { it.dc_SEHIR.contains(term,ignoreCase = true) } ?: listOf()
        return deger
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}