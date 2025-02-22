package data

import com.example.domain.currency.crypto.Crypto
import com.example.domain.currency.fiat.Fiat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TestData {

    val cryptoList: List<Crypto> =
        Gson().fromJson(CRYPTO_JSON_DATA, object : TypeToken<List<Crypto>>() {}.type)

    val fiatList: List<Fiat> =
        Gson().fromJson(FIAT_JSON_DATA, object : TypeToken<List<Fiat>>() {}.type)

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
        const val CRYPTO_JSON_DATA = "[\n" +
                "  {\n" +
                "    \"id\": \"BTC\",\n" +
                "    \"name\": \"Bitcoin\",\n" +
                "    \"symbol\": \"BTC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"ETH\",\n" +
                "    \"name\": \"Ethereum\",\n" +
                "    \"symbol\": \"ETH\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"XRP\",\n" +
                "    \"name\": \"XRP\",\n" +
                "    \"symbol\": \"XRP\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"BCH\",\n" +
                "    \"name\": \"Bitcoin Cash\",\n" +
                "    \"symbol\": \"BCH\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"LTC\",\n" +
                "    \"name\": \"Litecoin\",\n" +
                "    \"symbol\": \"LTC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"EOS\",\n" +
                "    \"name\": \"EOS\",\n" +
                "    \"symbol\": \"EOS\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"BNB\",\n" +
                "    \"name\": \"Binance Coin\",\n" +
                "    \"symbol\": \"BNB\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"LINK\",\n" +
                "    \"name\": \"Chainlink\",\n" +
                "    \"symbol\": \"LINK\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"NEO\",\n" +
                "    \"name\": \"NEO\",\n" +
                "    \"symbol\": \"NEO\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"ETC\",\n" +
                "    \"name\": \"Ethereum Classic\",\n" +
                "    \"symbol\": \"ETC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"ONT\",\n" +
                "    \"name\": \"Ontology\",\n" +
                "    \"symbol\": \"ONT\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"CRO\",\n" +
                "    \"name\": \"Crypto.com Chain\",\n" +
                "    \"symbol\": \"CRO\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"CUC\",\n" +
                "    \"name\": \"Cucumber\",\n" +
                "    \"symbol\": \"CUC\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"USDC\",\n" +
                "    \"name\": \"USD Coin\",\n" +
                "    \"symbol\": \"USDC\"\n" +
                "  }\n" +
                "]"
    }


}
