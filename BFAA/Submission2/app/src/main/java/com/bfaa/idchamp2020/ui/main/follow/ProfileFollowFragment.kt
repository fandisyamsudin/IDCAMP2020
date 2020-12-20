package com.bfaa.idchamp2020.ui.main.follow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.adapter.UserAdapter
import com.bfaa.idchamp2020.databinding.FragmentProfileFollowBinding
import com.bfaa.idchamp2020.model.StatusType
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.ui.base.BaseFragment
import com.bfaa.idchamp2020.ui.main.MainActivity
import com.bfaa.idchamp2020.ui.main.detail.DetailFragmentDirections
import com.bfaa.idchamp2020.util.changeNavigation
import com.bfaa.idchamp2020.util.showDialogWarning
import es.dmoral.toasty.Toasty


class ProfileFollowFragment : BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>(),
    UserAdapter.OnClick {
    private lateinit var sweetAlertDialog: SweetAlertDialog
    private var userAdapter = UserAdapter(this)
    override var getLayoutId: Int = R.layout.fragment_profile_follow
    override var getViewModel: Class<ProfileFollowViewModel> = ProfileFollowViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)

    companion object {
        private const val ARGUMENT_FRAGMENT_KEY = "argument_fragment_key"
        private const val ARGUMENT_FRAGMENT_VALUE = "argument_fragment_value"
        private const val TITLE = "Profile"

        fun newInstance(key: Int, username: String): ProfileFollowFragment {
            val bundle = Bundle().apply {
                putInt(ARGUMENT_FRAGMENT_KEY, key)
                putString(ARGUMENT_FRAGMENT_VALUE, username)
            }

            return ProfileFollowFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initDialog()
        initViewBinding()
    }

    private fun initViewBinding() {
        mViewBinding.apply {
            arguments?.apply {
                if (getInt(ARGUMENT_FRAGMENT_KEY) == 0) {
                    getString(ARGUMENT_FRAGMENT_VALUE)?.let { observeFollowers(it) }
                } else {
                    getString(ARGUMENT_FRAGMENT_VALUE)?.let { observeFollowing(it) }
                }
            }
            rvUser.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }
        }
    }

    private fun initDialog() {
        sweetAlertDialog = (activity as MainActivity).sweetAlertDialog
    }

    private fun initContentPlaceholder() {
        setContentPlaceholder(1)
    }

    private fun observeFollowers(username: String) {
        mViewBinding.apply {
            pb.visibility = View.VISIBLE
            mViewModel.getFollowers(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    mViewBinding.pb.visibility = View.GONE

                                    userAdapter.setList(data)
                                    if (data.isNotEmpty()) {
                                        setContentPlaceholder(1)
                                        userAdapter.setList(data)
                                    } else {
                                        setContentPlaceholder(5)
                                    }
                                }
                            }
                            StatusType.ERROR -> {
                                mViewBinding.pb.visibility = View.GONE
                                showDialogWarning(sweetAlertDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }

    private fun observeFollowing(username: String) {
        mViewBinding.apply {
            pb.visibility = View.VISIBLE
            mViewModel.getFollowing(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    mViewBinding.pb.visibility = View.GONE
                                    userAdapter.setList(data)
                                    if (data.isNotEmpty()) {
                                        setContentPlaceholder(1)
                                        userAdapter.setList(data)
                                    } else {
                                        setContentPlaceholder(6)
                                    }
                                }
                            }
                            StatusType.ERROR -> {
                                mViewBinding.pb.visibility = View.GONE
                                showDialogWarning(sweetAlertDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }

    override fun onUserClickListener(view: View, userGithub: UserGithub) {
        val action = DetailFragmentDirections.actionSelf(userGithub.login)
        view.changeNavigation(action)
        this.context?.let {
            Toasty.success(it, resources.getString(R.string.go_detail) + " " + userGithub.login , Toast.LENGTH_SHORT).show()
        }
    }
}
