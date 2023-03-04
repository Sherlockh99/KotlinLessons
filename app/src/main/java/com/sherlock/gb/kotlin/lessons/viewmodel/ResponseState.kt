package com.sherlock.gb.kotlin.lessons.viewmodel

sealed class ResponseState {
    object ServerSide : ResponseState()
    data class ClientSide(val codeError: Int) : ResponseState()
}