package com.informatika.argiandiputra_19100007_jadwalsholat.model

import com.google.gson.annotations.SerializedName

data class ResponseBarang(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("waktu_masuk")
	val waktuMasuk: String? = null,

	@field:SerializedName("waktu_habis")
	val waktuHabis: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jadwal_sholat")
	val jadwalSholat: String? = null
)
