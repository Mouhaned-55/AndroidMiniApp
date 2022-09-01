package com.example.androidminiapp.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmaticdevs.wallpaperapp.models.Photo
import com.example.androidminiapp.services.RetrofitInstance
import com.example.androidminiapp.ui.adapters.ImageItemAdapter
import com.example.androidminiapp.ui.fragments.Home
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var page: Int = 1
    var photos = MutableLiveData<List<Photo>>()
    private var sort: String = "popular"
    var filteredList = MutableLiveData<List<Photo>>()

    fun getImages(context: Context) {
        page = 1
        val getPost = RetrofitInstance.api.getRecentPhotos(page, 16, sort)
        getPost.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    Log.e("response", response.body().toString())
                    response.body()?.let {
                        photos.postValue(it)
                    }
                } else {
                    Toast.makeText(context, "response.body().toString()", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Toast.makeText(context, "Response failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun filterList(filterItem: String) {

        val templeList: ArrayList<Photo> = ArrayList()

        for (d in photos.value!!) {

            if (filterItem.lowercase() in d.user.username) {
                templeList.add(d)
            }
            if (templeList.isEmpty()){
                filteredList.postValue(photos.value)
            }
        }
        filteredList.postValue(templeList)
    }

}


