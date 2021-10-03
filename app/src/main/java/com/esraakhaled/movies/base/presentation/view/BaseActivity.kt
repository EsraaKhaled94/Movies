package com.esraakhaled.movies.base.presentation.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.esraakhaled.movies.base.presentation.viewmodel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    val viewModel: T by lazy {
        ViewModelProvider(this).get(getViewModelClass())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        bindLoadingObserver()
        bindErrorObserver()
        initView()
    }

    abstract fun initView()
    abstract fun getLayout(): Int
    abstract fun getViewModelClass(): Class<T>
    abstract fun handleLoading(b: Boolean)

    private fun bindLoadingObserver() = viewModel.getLoadingObservable().observe(this) {
        handleLoading(it)
    }
    private fun bindErrorObserver() =
        viewModel.getErrorObservable().observe(this) {
            if (it != null) {
                showError(it)
            }
        }

    private fun showError(throwable: Throwable?) {
        showDialog(null, throwable?.message)
    }

    private fun showDialog(title: String?, message: String?) {
        val dialog: AlertDialog =
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    dismissDialogCallback()
                }.create()
                .apply { setOwnerActivity(this@BaseActivity) }
        dialog.show()
    }

    private fun dismissDialogCallback() {
        viewModel.addErrorValue(null)
    }
}