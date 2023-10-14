package com.example.turistaapp.home.domain

import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.FakeDataBaseSource
import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class GetFlowLocationsDestinationFromDBTestUseCase {

    private lateinit var getFlowLocationsDestinationFromDB: GetFlowLocationsDestinationFromDB

    @RelaxedMockK
    private lateinit var tripDBRepository: TripDBRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getFlowLocationsDestinationFromDB = GetFlowLocationsDestinationFromDB(tripDBRepository)
    }

    @Test
    fun `invoke - repository return flow with Sting List - return Flow with LocationModel List` () = runTest{

        val expected = listOf(
            FakeDataBaseSource.locationModel,
            FakeDataBaseSource.locationModel,
        )

        val destinationLocationToString = expected.map {
            GsonConverter.toJson(it)
        }

        coEvery { tripDBRepository.getFlowLocationsFromDestination() } returns flow {
            emit(destinationLocationToString)
        }

        val result = getFlowLocationsDestinationFromDB()?.first()

        assertEquals(expected, result)
        assertEquals(2, result?.size)
    }

    @Test
    fun `invoke - repository return flow with emptyList - return Flow with emptyList` () = runTest{

        coEvery { tripDBRepository.getFlowLocationsFromDestination() } returns flow {
            emit(emptyList())
        }

        val result = getFlowLocationsDestinationFromDB()?.first()

        assertNull(result)

    }
}