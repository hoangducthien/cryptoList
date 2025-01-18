package com.example.data.currency.fiat

import com.example.data.common.AppDataBase
import com.example.domain.currency.fiat.Fiat
import com.example.domain.currency.fiat.FiatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FiatRepositoryImpl(
    private val fiatService: FiatService,
    private val db: AppDataBase,
    private val ioDispatcher: CoroutineDispatcher
) : FiatRepository {
    override suspend fun getFiatList(): Flow<List<Fiat>> {
        return withContext(ioDispatcher) {
            db.fiatDao().getFiatList().map { entities ->
                entities.map { it.toDomainModel() }
            }
        }
    }

    override suspend fun insertFiatList(): Boolean {
        return withContext(ioDispatcher) {
            val fiats = fiatService.getFiatList()
            val fiatEntities = fiats.map {
                it.toDbModel()
            }
            db.fiatDao().insertFiat(fiatEntities)
            true
        }
    }

    override suspend fun remoteAllFiat(): Boolean {
        return withContext(ioDispatcher) {
            db.fiatDao().deleteAll()
            true
        }
    }

}