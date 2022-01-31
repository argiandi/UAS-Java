package com.informatika.argiandiputra_19100007_jadwalsholat

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.argiandiputra_19100007_jadwalsholat.model.ResponseActionBarang
import com.informatika.argiandiputra_19100007_jadwalsholat.model.ResponseBarang
import kotlinx.android.synthetic.main.activity_insert_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "Tambahkan Jadwal Sholat"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val jadwal_sholat = et_jadwal_sholat.text
            val waktu_masuk = et_waktu_masuk.text
            val waktu_habis = et_waktu_habis.text
            if (jadwal_sholat.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jadwal Sholat Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (waktu_masuk.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Waktu Masuk Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (waktu_habis.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Waktu_Habis Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(jadwal_sholat.toString(), waktu_masuk.toString(), waktu_habis.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
        et_jadwal_sholat.text.clear()
        et_waktu_masuk.text.clear()
        et_waktu_habis.text.clear()

    }

    fun actionData(jadwal_sholat: String, waktu_masuk: String, waktu_habis: String) {
        koneksi.service.insertBarang(jadwal_sholat, waktu_masuk, waktu_habis)
            .enqueue(object : Callback<ResponseActionBarang> {
                override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseActionBarang>,
                    response: Response<ResponseActionBarang>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}

