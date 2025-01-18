package com.example.data.currency.crypto

import com.example.data.common.AppDataBase
import com.example.domain.currency.crypto.Crypto
import com.example.domain.currency.crypto.CryptoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CryptoRepositoryImpl(
    private val cryptoService: CryptoService,
    private val db: AppDataBase,
    private val ioDispatcher: CoroutineDispatcher
) : CryptoRepository {
    override suspend fun getCryptoList(): Flow<List<Crypto>> {
        return withContext(ioDispatcher) {
            db.cryptoDao().getCryptoList().map { cryptoEntities ->
                cryptoEntities.map { it.toDomainModel() }
            }
        }
    }

    override suspend fun insertCryptoList(): Boolean {
        return withContext(ioDispatcher) {
            val listCrypto = cryptoService.getCryptoList()
            val listCryptoEntity = listCrypto.map {
                it.toDbModel()
            }
            db.cryptoDao().insertCryptos(listCryptoEntity)
            true
        }
    }

    override suspend fun remoteAllCrypto(): Boolean {
        return withContext(ioDispatcher) {
            db.cryptoDao().deleteAll()
            true
        }
    }


}