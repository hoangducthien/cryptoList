package com.example.data.currency.fiat

import com.example.domain.currency.fiat.Fiat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


// fake remote data source for fiat list
class FiatService @Inject constructor() {

    suspend fun  getFiatList(): List<Fiat> {
        return Gson().fromJson(FIAT_JSON_DATA, object : TypeToken<List<Fiat>>() {}.type)
    }

    companion object {
        const val FIAT_JSON_DATA = "[\n" +
                "  {\n" +
                "    \"id\": \"EUR\",\n" +
                "    \"name\": \"Euro\",\n" +
                "    \"symbol\": \"€\",\n" +
                "    \"code\": \"EUR\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"GBP\",\n" +
                "    \"name\": \"British Pound\",\n" +
                "    \"symbol\": \"£\",\n" +
                "    \"code\": \"GBP\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"HKD\",\n" +
                "    \"name\": \"Hong Kong Dollar\",\n" +
                "    \"symbol\": \"\$\",\n" +
                "    \"code\": \"HKD\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"JPY\",\n" +
                "    \"name\": \"Japanese Yen\",\n" +
                "    \"symbol\": \"¥\",\n" +
                "    \"code\": \"JPY\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"AUD\",\n" +
                "    \"name\": \"Australian Dollar\",\n" +
                "    \"symbol\": \"\$\",\n" +
                "    \"code\": \"AUD\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"USD\",\n" +
                "    \"name\": \"United States Dollar\",\n" +
                "    \"symbol\": \"\$\",\n" +
                "    \"code\": \"USD\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"SGD\",\n" +
                "    \"name\": \"Singapore Dollar\",\n" +
                "    \"symbol\": \"\$\",\n" +
                "    \"code\": \"SGD\"\n" +
                "  }\n" +
                "]"
    }
}