package br.com.movieapp.feature.moviedetails.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource.LoadResult
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.feature.moviedetails.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository,
) : GetMovieDetailsUseCase {

    override fun invoke(params: GetMovieDetailsUseCase.Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val movieDetails = repository.getMovieDetails(params.movieId)
                val moviesSimilar = repository.getMoviesSimilar(
                    movieId = params.movieId,
                    pagingConfig = getPagingConfig()
                )
                emit(ResultData.Success(moviesSimilar to movieDetails))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ResultData.Failure(e))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ResultData.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20,
    )
}