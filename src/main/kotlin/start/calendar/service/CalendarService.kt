package start.calendar.service

import org.springframework.stereotype.Service
import start.calendar.model.HolidayList
import start.openapi.datagokr.model.SpcdeInfo
import start.openapi.datagokr.service.SpcdeInfoService

@Service
class CalendarService(val spcdeInfoService: SpcdeInfoService) {

    fun getHolidayList(): SpcdeInfo {
        val spcdeInfo = spcdeInfoService.getHoliDeInfo();


        if (spcdeInfo == null || spcdeInfo.response.body == null || spcdeInfo.response.body.items == null) {

        }

        var holidayList: List<HolidayList> = emptyList();

        for (item in spcdeInfo.response.body.items.item) {

            holidayList.toMutableList().add(HolidayList.from(item))
        }




        return spcdeInfo
    }

}