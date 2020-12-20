package com.bfaa.favoriteapp.ui.main.detail

import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bfaa.favoriteapp.BuildConfig
import com.bfaa.favoriteapp.R
import com.bfaa.favoriteapp.databinding.FragmentProfileBinding
import com.bfaa.favoriteapp.model.StatusType
import com.bfaa.favoriteapp.ui.base.BaseFragment


class DetailFragment : BaseFragment<FragmentProfileBinding, DetailViewModel>() {
    companion object{
        private const val TITLE = "Profile"
    }
    override var getLayoutId: Int = R.layout.fragment_profile
    override var getViewModel: Class<DetailViewModel> = DetailViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initViewBinding()
    }

    private fun initViewBinding() {
        mViewBinding.apply {
            arguments?.let {
                val user = DetailFragmentArgs.fromBundle(it).user
                val username = DetailFragmentArgs.fromBundle(it).username
                if (user != null){
                    setContentPlaceholder(1)
                    context?.let {
                            context -> observeCheckFavoriteUser(user.id, context)
                        this.user = user
                    }
                } else {
                    observeDetail(username)
                }

                viewpager.adapter =
                    FollowSectionsPagerAdapter(
                        childFragmentManager,
                        this@DetailFragment,
                        username,
                        user
                    )
                tab.setupWithViewPager(viewpager)
            }

            ivBack.setOnClickListener {
                activity?.onBackPressed()
            }

            ivShare.setOnClickListener{
                val name = BuildConfig.BASE_URL + "\"" + tvUsername
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, name)
                intent.type = "text/plain"
                startActivity(createChooser(intent, resources.getString(R.string.share_action)))
            }
        }
    }


    private fun initContentPlaceholder() {
        setContentPlaceholder(1)
    }

    private fun observeDetail(username: String) {
        mViewBinding.apply {
            pbProfile.visibility = View.VISIBLE
            mViewModel.getDetail(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            StatusType.SUCCESS-> {
                                it.data?.let { data ->
                                    setContentPlaceholder(1)
                                    context?.let { context ->
                                       observeCheckFavoriteUser(
                                           data.id,
                                           context
                                       )
                                   }
                                    user = data
                                }
                            }
                            StatusType.ERROR -> {
                                pbProfile.visibility = View.GONE
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }
    private fun observeCheckFavoriteUser(userId: Int, context: Context) {
        mViewBinding.apply {
            mViewModel.checkFavoriteUser(userId, context)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    user = data
                                }
                            }
                            StatusType.ERROR -> {
                                user = null
                            }
                        }
                    }
                })
        }
    }
}
