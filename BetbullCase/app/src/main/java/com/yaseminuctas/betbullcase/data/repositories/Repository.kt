package com.yaseminuctas.betbullcase.data.repositories

import com.yaseminuctas.betbullcase.data.network.Api
import com.yaseminuctas.betbullcase.data.network.Datum
import com.yaseminuctas.betbullcase.util.Const

class Repository(
    private val api: Api
) : SafeApiRequest() {

 //   suspend fun getMockData(): List<Datum> = apiRequest { api.getMockData() }




}