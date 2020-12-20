package com.bfaa.favoriteapp.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bfaa.favoriteapp.R
import com.bfaa.favoriteapp.adapter.UserAdapter
import com.bfaa.favoriteapp.databinding.FragmentHomeBinding
import com.bfaa.favoriteapp.model.StatusType
import com.bfaa.favoriteapp.model.UserGithub
import com.bfaa.favoriteapp.ui.base.BaseFragment
import com.bfaa.favoriteapp.util.changeNavigation
import es.dmoral.toasty.Toasty

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserAdapter.OnClick {
    companion object{
        private const val TITLE = "Favorite"
    }
    private var userAdapter = UserAdapter(this)
    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initViewBinding()
        observeUsersFavorite()
    }

    private fun observeUsersFavorite() {
        mViewBinding.apply {
            pb.visibility = View.VISIBLE
            context?.let { context ->
                mViewModel.getUsers(context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let { status ->
                            when (status.status) {
                                StatusType.SUCCESS -> {
                                    it.data?.let { data ->
                                        pb.visibility = View.GONE
                                        if (data.isNotEmpty()) {
                                            setContentPlaceholder(1)
                                            userAdapter.setList(data)
                                        } else {
                                            setContentPlaceholder(7)
                                        }
                                    }
                                }
                                StatusType.ERROR -> {
                                    pb.visibility = View.GONE
                                    setContentPlaceholder(2)
                                }
                            }
                        }
                    })
            }
        }
    }

    private fun initContentPlaceholder() {
        setContentPlaceholder(if (userAdapter.itemCount <= 0) 7 else 1)
    }

    private fun initViewBinding() {
        mViewBinding.apply {
            toolbar.apply {
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    if (it.itemId == R.id.action_change_language) {
                        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                        startActivity(intent)
                    }
                    false
                }
            }

            rvUser.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }
        }
    }

    override fun onUserClickListener(view: View, userGithub: UserGithub) {
        val action = HomeFragmentDirections.actionGoDetail(userGithub.login, userGithub)
        view.changeNavigation(action)
        this.context?.let {
            Toasty.success(it, resources.getString(R.string.go_detail) + " " + userGithub.login , Toast.LENGTH_SHORT).show()
        }
    }
}
