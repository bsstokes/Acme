package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

fun interface JsonFileReader {
    fun readJsonFile(): InputStream
}

class JsonFileInputDataRepository(
    private val jsonFileReader: JsonFileReader,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : InputDataRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getInputData(): SimpleResponse<InputData> {
        return withContext(ioDispatcher) {
            val jsonData = jsonFileReader.readJsonFile().use {
                Json.decodeFromStream<JsonData>(it)
            }

            InputData(
                drivers = jsonData.drivers.map { Driver(name = it) },
                shipments = jsonData.shipments.map { Shipment(address = it) }
            ).success()
        }
    }
}
