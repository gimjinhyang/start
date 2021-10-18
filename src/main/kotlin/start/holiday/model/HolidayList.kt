package start.holiday.model

import start.jpa.holiday.model.HolidayDayEntity
import start.jpa.holiday.model.HolidayMonthEntity
import start.openapi.datagokr.model.SpcdeInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class HolidayList(val year: Int, val month: Int, val day: Int, val name: String) {


    constructor(month: HolidayMonthEntity, day: HolidayDayEntity) : this(month.year, month.month, day.day, day.name)

    companion object {
        fun from(item: SpcdeInfo.Item): HolidayList {
            val date = LocalDate.parse(item.locdate, DateTimeFormatter.BASIC_ISO_DATE)
            return HolidayList(date.year, date.dayOfMonth, date.dayOfMonth, item.dateName)
        }
    }

}
