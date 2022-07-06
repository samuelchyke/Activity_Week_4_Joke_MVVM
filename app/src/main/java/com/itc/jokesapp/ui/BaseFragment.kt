package com.itc.jokesapp.ui

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.itc.jokesapp.ui.viewmodel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val jokesViewModel by lazy {
        ViewModelProvider(requireActivity())[JokesViewModel::class.java]
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("RETRY",) { _, _ ->
                action.invoke()
            }

    }

}