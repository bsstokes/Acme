package com.bsstokes.acme.data

import android.content.Context
import com.bsstokes.acme.R
import com.bsstokes.acme.app.data.repository.JsonFileReader
import java.io.InputStream

/**
 * A [JsonFileReader] that reads a JSON file via an Android raw resource.
 *
 * Be sure to run this in the background, e.g. with `Dispatchers.IO`, since it might take a while.
 */
class AndroidJsonFileReader(
    private val context: Context,
) : JsonFileReader {
    override fun readJsonFile(): InputStream {
        return context.resources.openRawResource(R.raw.data)
    }
}
