package com.example.androidminiapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidminiapp.fragments.Category
import com.example.androidminiapp.fragments.Home

class ViewPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    private val mFragment: MutableList<Fragment> = ArrayList()
    private val title: MutableList<String> = ArrayList()

    fun addFragment(fragment: Fragment, titles: String){
        if(fragment!=null)
        {
            mFragment.add(fragment)
        }
        if(titles !=null)
        {
            title.add(titles)
        }
    }
    override fun getItemCount(): Int {
        return mFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragment[position]
    }
}