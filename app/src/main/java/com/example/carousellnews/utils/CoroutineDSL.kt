package com.example.carousellnews.utils

import com.example.carousellnews.utils.AppCoroutineDispatchers.Companion.ioDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> ioReturnTask(
    block: suspend CoroutineScope.() -> T
): T {
    return startReturnTask(ioDispatcher, block)
}


suspend fun <T> startReturnTask(
    coroutineContext: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(coroutineContext) {
        return@withContext block()
    }
}

class AppCoroutineDispatchers{
    companion object {
        var uiDispatcher = Dispatchers.Main
        var computationDispatcher = Dispatchers.Default
        var ioDispatcher = Dispatchers.IO
    }
}