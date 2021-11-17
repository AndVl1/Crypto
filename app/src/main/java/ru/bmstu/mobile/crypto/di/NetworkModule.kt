package ru.bmstu.mobile.crypto.di

import android.content.Context
import coil.ImageLoader
import coil.util.CoilUtils
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.bmstu.mobile.crypto.network.ApiHelper
import ru.bmstu.mobile.crypto.network.ApiHelperImpl
import ru.bmstu.mobile.crypto.network.ApiService
import ru.bmstu.mobile.crypto.network.RequestInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .cache(CoilUtils.createDefaultCache(context))
            .build()
    }

    @Provides
    @Singleton
    fun providesImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient { okHttpClient }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://min-api.cryptocompare.com")
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}
