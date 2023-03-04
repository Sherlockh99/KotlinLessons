package com.sherlock.gb.kotlin.lessons.repository

import com.sherlock.gb.kotlin.lessons.viewmodel.ResponseState

interface OnServerResponseListener {
    fun onError(error: ResponseState)
}