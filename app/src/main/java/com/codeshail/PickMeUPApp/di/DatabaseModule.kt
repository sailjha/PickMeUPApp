package com.codeshail.pickmeupapp.di


import android.app.Application
import androidx.room.Room
import com.codeshail.pickmeupapp.data.datasources.local.LocalDataSource
import com.codeshail.pickmeupapp.data.datasources.local.MovieDataDao
import com.codeshail.pickmeupapp.data.datasources.local.MovieDataTypeConverter
import com.codeshail.pickmeupapp.data.datasources.local.MovieInfoDatabase
import com.codeshail.pickmeupapp.data.datasources.remote.RemoteDataSource
import com.codeshail.pickmeupapp.data.repositories.MovieInfoRepositoryImpl
import com.codeshail.pickmeupapp.data.repositories.WatchListInfoRepositoryImpl
import com.codeshail.pickmeupapp.domain.repositories.MovieInfoRepository
import com.codeshail.pickmeupapp.domain.repositories.WatchListInfoRepository
import com.codeshail.pickmeupapp.domain.usecases.GetMovieInfoUseCase
import com.codeshail.pickmeupapp.domain.usecases.WatchListInfoUseCase
import com.codeshail.pickmeupapp.utils.Constants.DATABASE_NAME
import com.codeshail.pickmeupapp.utils.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Application): MovieInfoDatabase {
        return Room.databaseBuilder(
            appContext,
            MovieInfoDatabase::class.java,
            DATABASE_NAME
        ).addTypeConverter(MovieDataTypeConverter(GsonParser(Gson())))
            .build()
    }


    @Singleton
    @Provides
    fun provideGetMovieInfoUseCase(repository: MovieInfoRepository): GetMovieInfoUseCase {
        return GetMovieInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideMovieInfoRepository(
        remote: RemoteDataSource,
        local: LocalDataSource,
        appContext: Application
    ): MovieInfoRepository {
        return MovieInfoRepositoryImpl(remote, local, appContext)
    }


    @Singleton
    @Provides
    fun provideWatchListInfoUseCase(repository: WatchListInfoRepository): WatchListInfoUseCase {
        return WatchListInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideWatchListInfoRepository(
        remote: RemoteDataSource,
        local: LocalDataSource,
        appContext: Application
    ): WatchListInfoRepository {
        return WatchListInfoRepositoryImpl(remote, local, appContext)
    }


    @Singleton
    @Provides
    fun provideDao(database: MovieInfoDatabase): MovieDataDao {
        return database.dao
    }
}