package data

import com.example.data.common.AppDataBase
import com.example.data.currency.fiat.FiatRepositoryImpl
import com.example.data.currency.fiat.FiatService
import com.example.data.currency.fiat.toDbModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class FiatRepositoryTest {

    private val service = mockk<FiatService>()
    private val database = mockk<AppDataBase>(relaxed = true)
    private val repository = FiatRepositoryImpl(
        service,
        database,
        UnconfinedTestDispatcher()
    )

    @Test
    fun getFiatList() = runTest {
        val testData = TestData()
        coEvery { database.fiatDao().getFiatList() } returns flow {
            emit(testData.fiatList.map {
                it.toDbModel()
            })
        }

        val result = repository.getFiatList().first()
        Assert.assertEquals(testData.fiatList.first().symbol, result.first().symbol)
    }

    @Test
    fun insertFiatTest() = runTest {
        val testData = TestData()
        coEvery { service.getFiatList() } returns testData.fiatList
        coEvery { database.fiatDao().insertFiat(any()) } returns Unit
        Assert.assertEquals(true, repository.insertFiatList())
    }

    @Test
    fun deleteFiatTest() = runTest {
        coEvery { database.fiatDao().deleteAll() } returns Unit
        Assert.assertEquals(true, repository.remoteAllFiat())
    }

}