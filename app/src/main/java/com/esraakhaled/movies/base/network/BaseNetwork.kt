package com.esraakhaled.movies.base.network

import com.esraakhaled.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseNetwork private constructor() {
    private var mOkHttpClient: OkHttpClient? = null
    private var mHttpLoggingInterceptor: HttpLoggingInterceptor? = null

    init {
        mHttpLoggingInterceptor = HttpLoggingInterceptor()
        mHttpLoggingInterceptor!!.apply {
            mHttpLoggingInterceptor!!.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    companion object {
        private var baseNetwork: BaseNetwork? = null

        fun getInstance(): BaseNetwork? {
            if (baseNetwork == null) baseNetwork = BaseNetwork()
            return baseNetwork
        }
    }

    private fun retrofit(
        requestClient: OkHttpClient,
    ): Retrofit? {
        val mRetrofitBuilder = Retrofit.Builder()
            .baseUrl(CloudConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(requestClient)

        return mRetrofitBuilder.build()
    }

    private fun getOkHttpClientBuilder(
        connectTimeout: Long,
        showLog: Boolean
    ): OkHttpClient.Builder {
        val okHttpClientBuilder: OkHttpClient.Builder
        if (mOkHttpClient == null) {
            mOkHttpClient = OkHttpClient()
            okHttpClientBuilder = mOkHttpClient!!.newBuilder()
            okHttpClientBuilder.connectTimeout(
                connectTimeout,
                TimeUnit.SECONDS
            )
            okHttpClientBuilder.readTimeout(connectTimeout, TimeUnit.SECONDS)
            okHttpClientBuilder.writeTimeout(connectTimeout, TimeUnit.SECONDS)
        } else {
            okHttpClientBuilder = mOkHttpClient!!.newBuilder()
            okHttpClientBuilder.connectTimeout(
                connectTimeout,
                TimeUnit.SECONDS
            )
            okHttpClientBuilder.readTimeout(connectTimeout, TimeUnit.SECONDS)
            okHttpClientBuilder.writeTimeout(connectTimeout, TimeUnit.SECONDS)
            okHttpClientBuilder.interceptors().clear()
        }
        okHttpClientBuilder.addInterceptor(
            Interceptor { chain: Interceptor.Chain ->
                val requestBuilder = chain.request().newBuilder()
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                response
            }
        )
        if (BuildConfig.DEBUG && showLog) {
            okHttpClientBuilder.addInterceptor(mHttpLoggingInterceptor!!)
        }
        return okHttpClientBuilder
    }

    fun <T> create(
        clazz: Class<T>?,
    ): T {
        val okHttpClient = getOkHttpClientBuilder(0, true).build()
        return retrofit(okHttpClient)!!.create(clazz)
    }
}
