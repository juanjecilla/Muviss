package com.scallop.muviss.di

import android.content.Context
import com.scallop.muviss.BuildConfig
import com.scallop.muviss.common.Properties
import com.scallop.muviss.common.SecurityUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Network {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getAPIKey(): String
}

fun createNetworkClient(baseUrl: String, context: Context) =
    retrofitClient(baseUrl, httpClient(context))

private fun httpClient(context: Context): OkHttpClient {
    val myCache = Cache(context.cacheDir, Properties.CACHE_SIZE_BYTES)

    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.cache(myCache)
    clientBuilder.addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url

        val ts = System.currentTimeMillis()
        val privateKey = Network.getAPIKey()
        val publicKey = "<<<PUBLIC_API_KEY>>>"

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", SecurityUtils.md5("$ts$privateKey$publicKey"))
            .build()

        val requestBuilder = original.newBuilder()
            .header("Cache-Control", "public, max-age=${Properties.MAX_SECONDS_VALID_CACHE}")
            .url(url)

        val request = requestBuilder.build()

        chain.proceed(request)
    }
    clientBuilder.readTimeout(Properties.NETWORK_CLIENT_TIMEOUT, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(Properties.NETWORK_CLIENT_TIMEOUT, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private fun getMoshi() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()