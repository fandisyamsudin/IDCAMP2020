package com.bfaa.idchamp2020.ui.main.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.adapter.UserAdapter
import com.bfaa.idchamp2020.databinding.FragmentFavoriteBinding
import com.bfaa.idchamp2020.model.StatusType
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.ui.base.BaseFragment
import com.bfaa.idchamp2020.ui.main.MainActivity
import com.bfaa.idchamp2020.util.changeNavigation
import com.bfaa.idchamp2020.util.showDialogWarning

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(),
    UserAdapter.OnClick {

    private var rvUserFavoriteAdapter = UserAdapter(this)
    private lateinit var sweetAlertDialog: SweetAlertDialog
    override var getLayoutId: Int = R.layout.fragment_favorite
    override var getViewModel: Class<FavoriteViewModel> = FavoriteViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Favorite")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initDialog()
        initViewBinding()
        observeFavorite()
    }

    private fun initViewBinding() {
        mViewBinding.apply {
            ivBack.setOnClickListener {
                activity?.onBackPressed()
            }

            rvUser.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = rvUserFavoriteAdapter
            }
        }

    }

    private fun initDialog() {
        sweetAlertDialog = (activity as MainActivity).sweetAlertDialog
    }

    private fun initContentPlaceholder() {
        setContentPlaceholder(if (rvUserFavoriteAdapter.itemCount <= 0) 7 else 1)
    }

    private fun observeFavorite() {
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
                                            rvUserFavoriteAdapter.setList(data)
                                        } else {
                                            setContentPlaceholder(7)
                                        }
                                    }
                                }
                                StatusType.ERROR -> {
                                    pb.visibility = View.GONE
                                    showDialogWarning(sweetAlertDialog, status.message ?: "Error", null)
                                    setContentPlaceholder(2)
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun onUserClickListener(view: View, userGithub: UserGithub) {
        val action = FavoriteFragmentDirections.actionFavoriteGoDetail(
            userGithub.login, userGithub
        )
        view.changeNavigation(action)
    }
}