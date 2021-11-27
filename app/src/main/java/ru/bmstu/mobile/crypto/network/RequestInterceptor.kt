package ru.bmstu.mobile.crypto.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(
                "api_key",
                "0bc9b816b9b02240d74c9dcad99641b922087439fc8963d639a4749e87b66588"
            )
            .build()
        request.url(url)
        val req = request.build()
        val response = chain.proceed(req)
        Log.d("INTERCEPTOR", req.toString())
        return response
    }
}
