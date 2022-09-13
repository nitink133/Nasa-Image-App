package `in`.obvious.nitin.nasaapp.di

import android.content.Context
import coil.ImageLoader
import coil.util.CoilUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule