package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

fun interface JsonFileReader {
    fun readJsonFile(): InputStream
}

class JsonFileInputDataRepository(
    private val jsonFileReader: JsonFileReader,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : InputDataRepository {
    override suspend fun getInputData(): SimpleResponse<InputData> {
        return withContext(ioDispatcher) {
            val driverName = jsonFileReader.readJsonFile().reader().use { it.readText() }
            InputData(
                drivers = listOf(Driver(driverName)),
                shipments = emptyList(),
            ).success()
        }
    }
}
