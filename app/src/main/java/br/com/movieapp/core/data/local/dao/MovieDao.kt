package br.com.movieapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.movieapp.core.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies")
    fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM Movies WHERE movieId = :movieId")
    suspend fun getFavoriteMovie(movieId: Int): MovieEntity?

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}