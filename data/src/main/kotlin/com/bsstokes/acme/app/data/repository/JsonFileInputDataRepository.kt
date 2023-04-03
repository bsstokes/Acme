package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.domain.model.InputData
import com.bsstokes.acme.domain.repository.InputDataRepository
import com.bsstokes.acme.domain.response.ErrorResponse
import com.bsstokes.acme.domain.response.SimpleResponse
import com.bsstokes.acme.domain.response.success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class JsonFileInputDataRepository(
    private val jsonFileReader: JsonFileReader,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : InputDataRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getInputData(): SimpleResponse<InputData> {
        return withContext(ioDispatcher) {
            runCatching {
                val jsonData = jsonFileReader.readJsonFile().use {
                    Json.decodeFromStream<JsonData>(it)
                }

                jsonData.toInputData().success()
            }.fold(
                onSuccess = { it },
                onFailure = { ErrorResponse },
            )
        }
    }
}
