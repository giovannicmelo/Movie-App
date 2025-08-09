package br.com.movieapp.core.data.remote

import br.com.movieapp.BuildConfig
import br.com.movieapp.core.util.API_KEY_PARAM
import br.com.movieapp.core.util.LANGUAGE_PARAM
import br.com.movieapp.core.util.LANGUAGE_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(LANGUAGE_PARAM, LANGUAGE_VALUE)
            .addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }
}