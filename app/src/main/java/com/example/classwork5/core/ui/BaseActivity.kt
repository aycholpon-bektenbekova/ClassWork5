package com.example.classwork5.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding, VM: BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkInternet()
        initObserver()
        initView()
        initListener()
        initViewModel()
    }

    open fun initViewModel() {}

    open fun initListener(){}

    open fun initView(){}

    open fun initObserver(){}

    open fun checkInternet(){}
}