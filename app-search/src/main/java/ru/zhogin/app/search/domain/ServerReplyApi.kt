package ru.zhogin.app.search.domain

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import ru.zhogin.app.search.domain.models.ServerReply

interface ServerReplyApi{
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getAlRemoteData() : Result<ServerReply>
}

internal fun ServerReplyApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
) : ServerReplyApi {
    return retrofit(baseUrl, okHttpClient, json).create()
}

internal fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json,
) : Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())
    val modifiedClient : OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedClient)
        .build()
}