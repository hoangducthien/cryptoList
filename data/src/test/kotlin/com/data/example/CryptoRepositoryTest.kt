package data

import com.example.data.common.AppDataBase
import com.example.data.currency.crypto.CryptoRepositoryImpl
import com.example.data.currency.crypto.CryptoService
import com.example.data.currency.crypto.toDbModel
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

class CryptoRepositoryTest {

    private val service = mockk<CryptoService>()
    private val database = mockk<AppDataBase>(relaxed = true)
    private val repository = CryptoRepositoryImpl(
        service,
        database,
        UnconfinedTestDispatcher()
    )

    @Test
    fun getCryptoTest() = runTest {
        val testData = TestData()
        coEvery { database.cryptoDao().getCryptoList() } returns flow {
            emit(testData.cryptoList.map {
                it.toDbModel()
            })
        }

        val result = repository.getCryptoList().first()
        Assert.assertEquals(testData.cryptoList.first().symbol, result.first().symbol)
    }

    @Test
    fun insertFiatTest() = runTest {
        val testData = TestData()
        coEvery { service.getCryptoList() } returns testData.cryptoList
        coEvery { database.cryptoDao().insertCryptos(any()) } returns Unit
        Assert.assertEquals(true, repository.insertCryptoList())
    }

    @Test
    fun deleteFiatTest() = runTest {
        coEvery { database.cryptoDao().deleteAll() } returns Unit
        Assert.assertEquals(true, repository.remoteAllCrypto())
    }

}