package com.system.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.system.data.model.Order
import com.system.data.repository.MongoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val repository: MongoRepository
) : ViewModel() {
    var data = MutableLiveData<List<Order>>()

    init {
        viewModelScope.launch {
            repository.getData().collect() {
                data.value = it
            }
        }
    }

    fun insertOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOrder(order)
        }
    }
}