package com.xpresscure.pagination.Retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xpresscure.pagination.Model.SearchResponse
import retrofit2.HttpException
import java.io.IOException

class DogsDataSource(val apiService: ApiService) : PagingSource<Int, SearchResponse>() {
    override fun getRefreshKey(state: PagingState<Int, SearchResponse>): Int? {
        return null
    }

    private val DEFAULT_PAGE_INDEX =1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResponse> {
        val pages = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = apiService.GetSearchItem(pages, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (pages == DEFAULT_PAGE_INDEX) null else pages - 1,
                nextKey = if (response.isEmpty()) null else pages + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}