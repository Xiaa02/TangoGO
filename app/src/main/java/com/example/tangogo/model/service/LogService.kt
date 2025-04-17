package com.example.tangogo.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
