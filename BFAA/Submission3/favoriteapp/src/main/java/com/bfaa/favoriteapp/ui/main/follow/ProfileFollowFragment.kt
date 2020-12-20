package com.bfaa.favoriteapp.ui.main.follow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bfaa.favoriteapp.R
import com.bfaa.favoriteapp.adapter.UserAdapter
import com.bfaa.favoriteapp.databinding.FragmentProfileFollowBinding
import com.bfaa.favoriteapp.model.StatusType
import com.bfaa.favoriteapp.model.UserGithub
import com.bfaa.favoriteapp.ui.base.BaseFragment
import com.bfaa.favoriteapp.ui.main.detail.DetailFragmentDirections
import com.bfaa.favoriteapp.util.changeNavigation
import es.dmoral.toasty.Toasty


class ProfileFollowFragment : BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>(),
    UserAdapter.OnClick {
    private var user: UserGithub? = null
    private var userAdapter = UserAdapter(this)
    override var getLayoutId: Int = R.layout.fragment_profile_follow
    override var getViewModel: Class<ProfileFollowViewModel> = ProfileFollowViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)

    companion object {
        private const val ARGUMENT_FRAGMENT_KEY = "argument_fragment_key"
        private const val ARGUMENT_FRAGMENT_VALUE = "argument_fragment_value"
        private const val TITLE = "Profile"
        private const val ARGUMENT_FRAGMENT_VALUE_USER ="argument_fragment_value_user"

        fun newInstance(key: Int, username: String, user: UserGithub?): ProfileFollowFragment {
            val bundle = Bundle().apply {
                putInt(ARGUMENT_FRAGMENT_KEY, key)
                putString(ARGUMENT_FRAGMENT_VALUE, username)
                putParcelable(ARGUMENT_FRAGMENT_VALUE_USER, user)
            }

            return ProfileFollowFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
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
                user = getParcelable(ARGUMENT_FRAGMENT_VALUE_USER)

            }
            rvUser.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }
        }
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
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }

    override fun onUserClickListener(view: View, userGithub: UserGithub) {
        val action = DetailFragmentDirections.actionSelf(userGithub.login, null)
        view.changeNavigation(action)
        this.context?.let {
            Toasty.success(it, resources.getString(R.string.go_detail) + " " + userGithub.login , Toast.LENGTH_SHORT).show()
        }
    }
}
