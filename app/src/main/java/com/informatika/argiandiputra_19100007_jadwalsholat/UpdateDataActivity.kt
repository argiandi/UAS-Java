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
import kotlinx.android.synthetic.main.activity_update_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        toolbar.title = "Edit Jadwal Sholat"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val idBarang = i.getStringExtra("IDBARANG")
        val jadwal_sholat = i.getStringExtra("JADWALSHOLAT")
        val waktu_masuk = i.getStringExtra("WAKTUMASUK")
        val waktu_habis = i.getStringExtra("WAKTUHABIS")

        et_jadwal_sholat.setText(jadwal_sholat)
        et_waktu_masuk.setText(waktu_masuk)
        et_waktu_habis.setText(waktu_habis)
        btn_submit.setOnClickListener {
            val jadwal_sholat = et_jadwal_sholat.text
            val waktu_masuk = et_waktu_masuk.text
            val waktu_habis = et_waktu_habis.text
            if (jadwal_sholat.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Jenis Kendaraan Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if (waktu_masuk.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Biaya Kendaraan Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            } else if (waktu_habis.isEmpty()) {
                Toast.makeText(
                    this@UpdateDataActivity,
                    "Kode Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                actionData(idBarang.toString(), jadwal_sholat.toString(), waktu_masuk.toString(), waktu_habis.toString())
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
        getData()
    }
    fun actionData(id : String, jenis_kendaraan : String, biaya_kendaraan : String, tanggal_masuk: String){
        koneksi.service.updateBarang(id, jenis_kendaraan, biaya_kendaraan, tanggal_masuk).enqueue(object :
            Callback<ResponseActionBarang> {
            override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseActionBarang>,
                response: Response<ResponseActionBarang>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG).show()
                    getData()
                }
            }
        })
    }
    fun getData(){
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful){
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@UpdateDataActivity,"UpdateDataActivity")


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                }
            }

        })
    }
}