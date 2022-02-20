package com.xpresscure.pagination.Retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.xpresscure.pagination.Model.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

    fun GetAllDogs():Flow<PagingData<SearchResponse>> = Pager(config = PagingConfig(20, enablePlaceholders = false) ) {

        DogsDataSource(apiService)

    }.flow.cachedIn(viewModelScope)
}