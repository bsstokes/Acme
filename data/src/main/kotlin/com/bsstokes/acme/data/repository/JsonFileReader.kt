package com.bsstokes.acme.data.repository

import java.io.InputStream

fun interface JsonFileReader {
    fun readJsonFile(): InputStream
}
