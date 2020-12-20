package com.bfaa.idchamp2020.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.adapter.UserAdapter
import com.bfaa.idchamp2020.databinding.FragmentHomeBinding
import com.bfaa.idchamp2020.model.StatusType
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.ui.base.BaseFragment
import com.bfaa.idchamp2020.ui.main.MainActivity
import com.bfaa.idchamp2020.util.changeNavigation
import com.bfaa.idchamp2020.util.hideKeyboard
import com.bfaa.idchamp2020.util.showDialogWarning
import es.dmoral.toasty.Toasty

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserAdapter.OnClick {
    companion object{
        private const val TITLE = "Home"
    }
    private lateinit var sweetAlertDialog: SweetAlertDialog
    private var userAdapter = UserAdapter(this)
    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData(TITLE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentPlaceholder()
        initDialog()
        initViewBinding()
    }

    private fun initViewBinding() {
        mViewBinding.apply {
            toolbar.apply {
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    if (it.itemId == R.id.action_setting) {
                        val goSetting = HomeFragmentDirections.actionGoSetting()
                        view?.changeNavigation(goSetting)
                    } else {
                        val goFavorite = HomeFragmentDirections.actionGoFavorite()
                        view?.changeNavigation(goFavorite)
                    }
                    false
                }
            }

            rvUser.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }

            etSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    var handled = false
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        searchUser()
                        handled = true
                    }
                    handled
                }
            }
        }
    }

    private fun initDialog() {
        sweetAlertDialog = (activity as MainActivity).sweetAlertDialog
    }

    private fun initContentPlaceholder() {
        setContentPlaceholder(if (userAdapter.itemCount <= 0) 3 else 1)
    }

    private fun searchUser() {
        mViewBinding.apply {
            if (!etSearch.text.isBlank()) {
                rvUser.requestFocus()
                pb.visibility = View.VISIBLE
                context?.hideKeyboard(requireView())
                observeUserSearch(etSearch.text.toString())
            }
        }
    }

    private fun observeUserSearch(keyword: String) {
        mViewModel.getUserSearch(keyword)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        StatusType.SUCCESS -> {
                            it.data?.let { data ->
                                mViewBinding.pb.visibility = View.GONE
                                if (data.total_count > 0) {
                                    setContentPlaceholder(1)
                                    userAdapter.setList(data.items)
                                } else {
                                    setContentPlaceholder(4)
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

    override fun onUserClickListener(view: View, userGithub: UserGithub) {
        val action = HomeFragmentDirections.actionGoDetail(userGithub.login, null)
        view.changeNavigation(action)
        this.context?.let {
            Toasty.success(it, resources.getString(R.string.go_detail) + " " + userGithub.login , Toast.LENGTH_SHORT).show()
        }
    }
}
