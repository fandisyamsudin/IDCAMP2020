package com.bfaa.idchamp2020.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bfaa.idchamp2020.helper.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel> : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var mViewBinding: T
    lateinit var mViewModel: VM
    abstract var getLayoutId: Int
    abstract var getViewModel: Class<VM>
    abstract var title: MutableLiveData<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        mViewModel = ViewModelProvider(this, factory)[getViewModel]
        title.observe(viewLifecycleOwner, Observer {
        })
        return mViewBinding.root
    }

    fun setContentPlaceholder(condition: Int) {
       com.bfaa.idchamp2020.util.setContentPlaceholder(condition, mViewBinding)
    }

}