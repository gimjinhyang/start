package start.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import start.calendar.service.CalendarService
import start.openapi.datagokr.model.SpcdeInfo

@RestController
@RequestMapping("/api/calendar")
class CalendarResource(val calendarService: CalendarService) {

    @GetMapping("/holiday")
    fun getHoliday(): SpcdeInfo {
        return calendarService.getHolidayList()
    }

}