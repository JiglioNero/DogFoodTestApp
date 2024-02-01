package jiglionero.dogfoodtestapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jiglionero.dogfoodtestapp.data.entity.Breed

abstract class NotPagedDataSource<T : Any>: PagingSource<Int, T>() {

    abstract suspend fun getItems(): List<T>

    private fun getPositionalItems(items: List<T>, page: Int, loadSize: Int): List<T> {
        var result = emptyList<T>()

        if (items.size > page * loadSize && items.size < (page + 1) * loadSize) {
            result = items.subList(page * loadSize, items.size)
        }
        if (items.size > (page + 1) * loadSize) {
            result = items.subList(page * loadSize, (page + 1) * loadSize)
        }

        return result
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val allItems = getItems()
        val page = params.key ?: 0
        val pageItems = getPositionalItems(allItems, page, params.loadSize)

        return LoadResult.Page(
            data = pageItems,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (pageItems.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}