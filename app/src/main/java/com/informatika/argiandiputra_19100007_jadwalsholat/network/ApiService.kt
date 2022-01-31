package com.informatika.argiandiputra_19100007_jadwalsholat

import com.informatika.argiandiputra_19100007_jadwalsholat.model.ResponseActionBarang
import com.informatika.argiandiputra_19100007_jadwalsholat.model.ResponseAdmin
import com.informatika.argiandiputra_19100007_jadwalsholat.model.ResponseBarang
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getBarang(): Call<ResponseBarang>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @Field("jadwal_sholat") jadwal_sholat: String?,
        @Field("waktu_masuk") waktu_masuk: String?,
        @Field("waktu_habis") waktu_habis: String?,
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("jadwal_sholat") jadwal_sholat: String?,
        @Field("waktu_masuk") waktu_masuk: String?,
        @Field("waktu_habis") waktu_habis: String?,
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseAdmin>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseAdmin>


}