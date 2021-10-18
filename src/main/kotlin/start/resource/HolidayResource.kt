package start.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import start.holiday.model.HolidayList
import start.holiday.service.HolidayService

@RestController
@RequestMapping("/api/holiday")
class HolidayResource(val holidayService: HolidayService) {

    @GetMapping("/list")
    fun getList(year: Int, month: Int): List<HolidayList> {
        return holidayService.getList(year, month)
    }

}