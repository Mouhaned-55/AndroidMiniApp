package com.example.androidminiapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmaticdevs.wallpaperapp.models.Photo
import com.example.androidminiapp.R
import com.example.androidminiapp.adapters.ImageItemAdapter
import com.example.androidminiapp.databinding.FragmentHomeBinding
import com.example.androidminiapp.services.RetrofitInstance
import com.example.androidminiapp.simpleActivities.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home() : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ImageItemAdapter
    private var page: Int = 1
    private var photos: MutableList<Photo> = ArrayList()
    private var sort: String = "popular"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        initRecyclerView()
        getImages()
        return view

    }

    private fun getImages() {
        page = 1
        val getPost = RetrofitInstance.api.getRecentPhotos(page, 16, sort)
        getPost.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    photos.clear()
                    Log.d("response", response.body().toString())
                    response.body()?.let { photos.addAll(it) }
                    adapter.notifyDataSetChanged()
                } else
                    Log.d("response", response.body().toString())
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("Response", "Failed")
            }
        })

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e("beforeTextChanged", "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("onTextChanged", "onTextChanged")
            }

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }
        })
    }


    private fun initRecyclerView() {
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ImageItemAdapter(photos, requireContext())
        binding.homeRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : ImageItemAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(requireContext(), DetailsActivity::class.java)

                intent.putExtra("username", photos[position].user.username)

                intent.putExtra("description", photos[position].description)

                intent.putExtra("photo", photos[position].url.regular)

                intent.putExtra("created_at", photos[position].created_at)

                intent.putExtra("profile_image", photos[position].user.profileImage.large)

                startActivity(intent)
            }
        })
    }

    private fun filterList(filterItem: String) {

        var templeList: MutableList<Photo> = ArrayList()

        for (d in photos) {

            if (filterItem.lowercase() in d.user.username) {

                templeList.add(d)

            }

        }

        adapter.updateList(templeList)
    }


}


