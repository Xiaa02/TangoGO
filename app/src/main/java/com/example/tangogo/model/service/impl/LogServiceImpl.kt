package com.example.tangogo.model.service.impl

import android.util.Log
import com.example.tangogo.model.service.LogService
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {

    override fun logNonFatalCrash(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }

    override fun log(message: String) {
        Log.d("TangoGO", message)
    }
}
