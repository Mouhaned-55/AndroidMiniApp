package com.example.androidminiapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmaticdevs.wallpaperapp.models.Photo
import com.example.androidminiapp.R
import com.example.androidminiapp.ui.adapters.ImageItemAdapter
import com.example.androidminiapp.databinding.FragmentHomeBinding
import com.example.androidminiapp.mapper.toPhotoUi
import com.example.androidminiapp.models.PhotoUi
import com.example.androidminiapp.ui.activities.DetailsActivity
import com.example.androidminiapp.ui.adapters.StoryItemAdapter
import com.example.androidminiapp.ui.popups.StoryPopUp
import com.example.androidminiapp.viewModel.HomeViewModel

class Home() : Fragment() {

    private val homePageViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ImageItemAdapter
    private lateinit var storiesAdapter: StoryItemAdapter

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

        binding.lifecycleOwner = this
        binding.homePageViewModel = homePageViewModel


        homePageViewModel.getImages(requireContext())

        homePageViewModel.photos.observe(viewLifecycleOwner, Observer { photos ->
            initRecyclerView(photos = photos)
        })

        homePageViewModel.filteredList.observe(viewLifecycleOwner, Observer { photos ->
            initRecyclerView(photos = photos)
        })

        return view

    }


    private fun initRecyclerView(photos: List<Photo>) {
        binding.homeRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
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


        //Setup UI For Stories Pictures
        binding.storiesRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        val photoListUi = photos.map {
            it.toPhotoUi()
        }
        storiesAdapter = StoryItemAdapter(this.requireContext(), ArrayList(photoListUi))

        binding.storiesRecyclerView.adapter = storiesAdapter

        storiesAdapter.setOnClickListener(object : StoryItemAdapter.OnClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClick(position: Int, item: PhotoUi) {
                StoryPopUp(requireContext(), item.url!!).show()
                item.seen = true
            }
        })

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                homePageViewModel.filterList(s.toString())
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


