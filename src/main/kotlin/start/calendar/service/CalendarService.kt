package start.calendar.service

import org.springframework.stereotype.Service
import start.openapi.datagokr.model.SpcdeInfo
import start.openapi.datagokr.service.SpcdeInfoService

@Service
class CalendarService(val spcdeInfoService: SpcdeInfoService) {

    fun getHolidayList(): SpcdeInfo {

        return spcdeInfoService.getHoliDeInfo()
    }

}