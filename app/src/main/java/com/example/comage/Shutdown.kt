package com.example.comage

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class Shutdown(private val ip: String = "127.0.0.1", private val port: String = "8000"): Thread() {

    override fun run() {
        val url = URL("http://$ip:$port/shutdown")
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"

        val responseCode = conn.responseCode
        println("Response code: $responseCode")

        val inputStream = conn.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()

        var line = reader.readLine()
        while (line != null) {
            response.append(line)
            line = reader.readLine()
        }

        reader.close()
        inputStream.close()

        println("Response: $response")
    }
}