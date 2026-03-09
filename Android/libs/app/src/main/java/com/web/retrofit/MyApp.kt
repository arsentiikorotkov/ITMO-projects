package com.web.retrofit

import android.app.Application
import androidx.room.Room
import com.web.room.MessagesDb
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApp : Application() {
    lateinit var api: MessageAPI
    lateinit var db: MessagesDb

    override fun onCreate() {
        super.onCreate()
        instance = this
        val retrofit = Retrofit.Builder()
            .baseUrl("http://213.189.221.170:8008")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        api = retrofit.create(MessageAPI::class.java)

        db = Room.databaseBuilder(applicationContext, MessagesDb::class.java, "messages").build()
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}
