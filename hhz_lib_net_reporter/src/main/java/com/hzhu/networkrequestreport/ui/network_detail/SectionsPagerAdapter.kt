package com.hzhu.networkrequestreport.ui.network_detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hzhu.networkrequestreport.R
import com.hzhu.networkrequestreport.report.entity.NetworkQueue

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager,val networkQueue: NetworkQueue) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
       return if (position==0) {
           NetworkDetailsFragment(networkQueue.request)
        }else{
           NetworkDetailsFragment(networkQueue.response)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}