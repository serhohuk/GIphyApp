package com.serhohuk.giphyapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


    fun View.hideKeyboard() {
        val context: Context = this.context
        val service = context.getSystemService(Context.INPUT_METHOD_SERVICE)
        val imm = service as InputMethodManager
        imm?.hideSoftInputFromWindow(this.windowToken, 0)
    }

