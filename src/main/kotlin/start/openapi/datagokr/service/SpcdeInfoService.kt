package start.openapi.datagokr.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import start.openapi.datagokr.model.SpcdeInfo

@Component
class SpcdeInfoService() {

    val serviceKey = "FdZXQ75RUUh/0rwuqiKT7Xc+ghxyaURhnpa8C9S4Q5LU25dHwM+RmiTCZhaIT0xQeqLC+3QrhdGDBNKv6lr9eg=="
    val domain = "http://apis.data.go.kr"
    val api_holiday = "$domain/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo"

    fun getHoliDeInfo(year: Int, month: Int): MutableList<SpcdeInfo.Item> {

        val first = getSpcdeInfo(year, month, 1)
        if (first == null || first.response.body == null || first.response.body.items == null || first.response.body.items.item == null) {
            return mutableListOf()
        }

        val itemList = mutableListOf(first.response.body.items.item);

        if (first.response.body.totalCount > 1) {
            for (p in 2..first.response.body.totalCount) {
                var extra = getSpcdeInfo(year, month, p)
                itemList.add(extra.response.body.items.item)
            }
        }

        return itemList
    }

    private fun getSpcdeInfo(year: Int, month: Int, pageNo: Int): SpcdeInfo {
        val client = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        val response: SpcdeInfo

        runBlocking {
            response = client.get(api_holiday) {
                parameter("ServiceKey", serviceKey)
                parameter("_type", "json")
                parameter("numOfRows", 1)
                parameter("pageNo", pageNo)
                parameter("solYear", year)
                parameter("solMonth", month)
            }
        }

        client.close()

        println(response)

        return response
    }

}