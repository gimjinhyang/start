package start.resource

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import start.holiday.service.HolidayService

@RestController
@RequestMapping("/api/holiday")
class HolidayResource(val holidayService: HolidayService) {

    @GetMapping("/list")
    fun getList(year: Int, month: Int): ResponseEntity<Any> {
        val holidayList = holidayService.getList(year, month)
        return ResponseEntity.ok(mapOf("list" to holidayList))
    }

}