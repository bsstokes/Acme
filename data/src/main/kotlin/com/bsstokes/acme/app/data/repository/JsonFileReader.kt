package com.bsstokes.acme.app.data.repository

import java.io.InputStream

fun interface JsonFileReader {
    fun readJsonFile(): InputStream
}
