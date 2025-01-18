package com.example.domain.currency.fiat

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFiatListUseCase @Inject constructor(private val fiatRepository: FiatRepository) {

    suspend operator fun invoke(): Flow<List<Fiat>> {
        return fiatRepository.getFiatList()
    }
}