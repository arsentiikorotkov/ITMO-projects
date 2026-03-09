package com.web.retrofit

import com.web.room.Message
import com.web.room.MessageForSendingImage
import com.web.room.MessageForSendingText
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface MessageAPI {
    @GET("/1ch?")
    suspend fun getListMessages(
        @Query("lastKnownId") lastKnownId: Long,
        @Query("reverse") reverse: Boolean,
        @Query("limit") limit: Int
    ): Response<List<Message>>

    @GET("/thumb/{path}")
    suspend fun getImageThumb(
        @Path("path") path: String,
    ): Response<ResponseBody>

    @GET("/img/{path}")
    suspend fun getImageHigh(
        @Path("path") path: String,
    ): Response<ResponseBody>

    @POST("/1ch")
    suspend fun postText(@Body text: MessageForSendingText): Response<ResponseBody>

    @Multipart
    @POST("/1ch")
    suspend fun postImage(
        @Part part: MultipartBody.Part,
        @Part("msg") messageForSendImage: MessageForSendingImage
    ): Response<ResponseBody>
}