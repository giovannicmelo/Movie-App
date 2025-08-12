package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.data.remote.response.SearchResponse
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.feature.searchmovie.data.mapper.toMovieSearch
import br.com.movieapp.feature.searchmovie.domain.source.MovieSearchRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

private const val LIMIT_PAGE = 20

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource,
) : PagingSource<Int, MovieSearch>() {

    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT_PAGE) ?: anchorPage?.nextKey?.minus(LIMIT_PAGE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber: Int = params.key ?: 1
            val response: SearchResponse = remoteDataSource.getSearchMovies(pageNumber, query)
            val movies: List<SearchResult> = response.results

            LoadResult.Page(
                data = movies.toMovieSearch(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1,
            )
        } catch (e: IOException) {
            e.printStackTrace()
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}