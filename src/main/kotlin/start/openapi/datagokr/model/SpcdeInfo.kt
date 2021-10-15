package start.openapi.datagokr.model

import kotlinx.serialization.Serializable

@Serializable
data class SpcdeInfo(val response: Response) {

    @Serializable
    data class Response(val header: Header, val body: Body)

    @Serializable
    data class Header(val resultCode: String, val resultMsg: String)

    @Serializable
    data class Body(val items: Items, val numOfRows: Int, val pageNo: Int, val totalCount: Int)

    @Serializable
    data class Items(val item: List<Item>)

    @Serializable
    data class Item(
        val locdate: String, val seq: Int, val dateKind: String, val isHoliday: String, val dateName: String
    )

}

