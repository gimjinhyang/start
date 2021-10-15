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

    fun getHoliDeInfo(): SpcdeInfo {

        val api = "$domain/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo"

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
            response = client.get(api) {
                parameter("ServiceKey", serviceKey)
                parameter("_type", "json")
                parameter("numOfRows", 20)
                parameter("solYear", 2021)
                parameter("solMonth", 10)
            }
        }

        client.close()
        println(response)

        return response
    }

}