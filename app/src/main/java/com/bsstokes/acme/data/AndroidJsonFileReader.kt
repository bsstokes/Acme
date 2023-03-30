package com.bsstokes.acme.data

import android.content.Context
import com.bsstokes.acme.R
import com.bsstokes.acme.app.data.repository.JsonFileReader
import java.io.InputStream

class AndroidJsonFileReader(
    private val context: Context,
) : JsonFileReader {
    override fun readJsonFile(): InputStream {
        return context.resources.openRawResource(R.raw.data)
    }
}
