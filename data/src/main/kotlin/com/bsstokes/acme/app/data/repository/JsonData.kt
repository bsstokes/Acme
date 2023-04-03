package com.bsstokes.acme.app.data.repository

import kotlinx.serialization.Serializable

@Serializable
internal data class JsonData(
    val shipments: List<String>,
    val drivers: List<String>,
)
