package ru.bmstu.mobile.crypto.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.bmstu.mobile.crypto.model.DataX
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoshiAdapter {

    @Provides
    @Singleton
    fun providesDataXMoshiAdapter(): JsonAdapter<DataX> {
        return Moshi.Builder().build().adapter(DataX::class.java)
    }
}