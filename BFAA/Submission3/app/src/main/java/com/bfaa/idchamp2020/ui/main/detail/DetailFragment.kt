package com.bfaa.idchamp2020.ui.main.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.idchamp2020.BuildConfig
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.databinding.FragmentProfileBinding
import com.bfaa.idchamp2020.model.StatusType
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.ui.base.BaseFragment
import com.bfaa.idchamp2020.ui.main.MainActivity
import com.bfaa.idchamp2020.util.showDialogWarning
import es.dmoral.toasty.Toasty


class DetailFragment : BaseFragment<FragmentProfileBinding, DetailViewModel>() {
    companion object{
        private const val TITLE = "Profile"
    }
    private lateinit var sweetAlertDialog: SweetAlertDialog
    override var getLayoutId: Int = R.layout.fragment_profile
    override var getViewModel: Class<DetailViewModel> = DetailViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)
    private var userGithub: UserGithub? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initDialog()
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

            favoriteActionButton.setOnClickListener {
                if (userGithub != null) {
                    deleteFavorite()
                } else {
                    addFavorite()
                }
            }
        }
    }

    private fun initDialog() {
        sweetAlertDialog = (activity as MainActivity).sweetAlertDialog
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
                                showDialogWarning(sweetAlertDialog, status.message ?: "Error", null)
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
                                    userGithub = data
                                }
                            }
                            StatusType.ERROR -> {
                                userGithub = null
                            }
                        }
                        checkIsFavorite()
                    }
                })
        }
    }

    private fun addFavorite() {
        mViewBinding.user?.let { data ->
            context?.let { context ->
                mViewModel.addFavoriteUser(data, context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let {
                            userGithub = data
                            Toasty.success(context, getString(R.string.add_to_favorite), Toast.LENGTH_SHORT, false).show();
                        }
                        checkIsFavorite()
                    })
            }
        }
    }

    private fun deleteFavorite() {
        mViewBinding.user?.let { data ->
            context?.let { context ->
                mViewModel.deleteFavoriteUser(data, context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let {
                            userGithub = null
                            Toasty.info(context, getString(R.string.delete_to_favorite), Toast.LENGTH_SHORT, false).show();
                        }
                        checkIsFavorite()
                    })
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun checkIsFavorite() {
        val drawable =
            if (userGithub != null){
                resources.getDrawable(R.drawable.ic_favorite_on, null)
            } else {
                resources.getDrawable(R.drawable.ic_favorite_off, null)
            }

        mViewBinding.apply {
            favoriteActionButton.setImageDrawable(drawable)
            if (pbProfile.isShown){
                pbProfile.visibility = View.GONE
            }
        }
    }
}
