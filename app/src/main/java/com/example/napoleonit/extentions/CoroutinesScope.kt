package com.example.napoleonit.extentions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithErrorHandler(
    block: suspend () -> Unit,
    onError: (Throwable) -> Unit = { _ -> }
) {
    launch(CoroutineExceptionHandler { context, throwable ->
        onError(throwable)
    }) {
        block()
    }
}