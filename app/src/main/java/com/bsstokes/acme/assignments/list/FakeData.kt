package com.bsstokes.acme.assignments.list

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment

object FakeData {
    private val shipments = listOf(
        "215 Osinski Manors",
        "9856 Marvin Stravenue",
        "7127 Kathlyn Ferry",
        "987 Champlin Lake",
        "63187 Volkman Garden Suite 447",
        "75855 Dessie Lights",
        "1797 Adolf Island Apt. 744",
        "2431 Lindgren Corners",
        "8725 Aufderhar River Suite 859",
        "79035 Shanna Light Apt. 322",
    )

    private val drivers = listOf(
        "Everardo Welch",
        "Orval Mayert",
        "Howard Emmerich",
        "Izaiah Lowe",
        "Monica Hermann",
        "Ellis Wisozk",
        "Noemie Murphy",
        "Cleve Durgan",
        "Murphy Mosciski",
        "Kaiser Sose",
    )

    val assignments by lazy {
        drivers.zip(shipments).map { (driverName, shipmentAddress) ->
            Assignment(
                driver = Driver(name = driverName),
                shipment = Shipment(address = shipmentAddress),
            )
        }
    }

    val assignmentsListUiState by lazy { assignments.toUiState() }
}
