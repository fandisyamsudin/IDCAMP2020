package com.bfaa.idchamp2020.ui.main.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.ui.main.follow.ProfileFollowFragment

class FollowSectionsPagerAdapter(
    fm: FragmentManager,
    fragment: Fragment,
    private val username: String
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = listOf(
        fragment.getString(R.string.follower),
        fragment.getString(R.string.following)
    )

    override fun getItem(position: Int): Fragment {
        return ProfileFollowFragment.newInstance(position, username)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getCount(): Int {
        return tabs.size
    }
}