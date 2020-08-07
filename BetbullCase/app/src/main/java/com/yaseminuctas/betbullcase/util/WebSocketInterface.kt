package com.yaseminuctas.betbullcase.util

import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString

interface WebSocketInterface {

    fun onOpen(webSocket: WebSocket, response: Response)

    fun onMessage(webSocket: WebSocket, text: String)

    fun onMessage(webSocket: WebSocket, bytes: ByteString)

    fun onClosing(webSocket: WebSocket, code: Int, reason: String)

    fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?)

    fun setToolbarTitle(title: String)

    fun getText(text: String)
}