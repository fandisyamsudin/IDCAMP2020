package com.bfaa.idchamp2020.ui.main.detail

import android.content.Intent
import android.content.Intent.createChooser
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.idchamp2020.BuildConfig
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.databinding.FragmentProfileBinding
import com.bfaa.idchamp2020.model.StatusType
import com.bfaa.idchamp2020.ui.base.BaseFragment
import com.bfaa.idchamp2020.ui.main.MainActivity
import com.bfaa.idchamp2020.util.showDialogWarning


class DetailFragment : BaseFragment<FragmentProfileBinding, DetailViewModel>() {
    companion object{
        private const val TITLE = "Profile"
    }
    private lateinit var sweetAlertDialog: SweetAlertDialog
    override var getLayoutId: Int = R.layout.fragment_profile
    override var getViewModel: Class<DetailViewModel> = DetailViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initDialog()
        initViewBinding()
    }

    private fun initViewBinding() {
        mViewBinding.apply {
            arguments?.let {
                val username = DetailFragmentArgs.fromBundle(it).username
                observeDetail(username)
                viewpager.adapter =
                    FollowSectionsPagerAdapter(
                        childFragmentManager,
                        this@DetailFragment,
                        username
                    )
                tab.setupWithViewPager(viewpager)
            }

            ivBack.setOnClickListener {
                activity?.onBackPressed()
            }

            ivShare.setOnClickListener{
                val name = BuildConfig.BASE_URL + "\"" + tvUsername //tvName.text.toString()
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, name)
                intent.type = "text/plain"
                startActivity(createChooser(intent, resources.getString(R.string.share_action)))
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
                                pbProfile.visibility = View.GONE
                                it.data?.let { data ->
                                    setContentPlaceholder(1)
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
                }
                )
        }
    }
}
