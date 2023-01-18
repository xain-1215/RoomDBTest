package com.example.demo_fragmentandretrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class Bfragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.b_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. 透過RetrofitManager取得連線基底
        val myAPIService: MyAPIService = RetrofitManager.getInstance().api

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        val call = myAPIService.albums

        val albums = Albums(1, 1, "test")
        val call2 = myAPIService.postAlbums(albums)

        // 4. 執行call
        call.enqueue(object : Callback<Albums> {
            override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                val title = response.body()!!.title
                Log.d("title", title)
            }

            override fun onFailure(call: Call<Albums>, t: Throwable) {
                // 連線失敗
            }
        })

        call2.enqueue(object : Callback<Albums> {
            override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                val title = response.body()!!.title
                Log.d("wah: ", title)
            }

            override fun onFailure(call: Call<Albums>, t: Throwable) {
            }


        })

    }
}