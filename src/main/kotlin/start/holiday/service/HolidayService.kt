package start.holiday.service

import org.springframework.stereotype.Service
import start.holiday.model.HolidayList
import start.mysql.jpa.holiday.model.HolidayDayEntity
import start.mysql.jpa.holiday.model.HolidayMonthEntity
import start.mysql.jpa.holiday.repository.HolidayMonthRepository
import start.openapi.datagokr.model.SpcdeInfo
import start.openapi.datagokr.service.SpcdeInfoService
import kotlin.streams.toList

@Service
class HolidayService(val holidayMonthRepository: HolidayMonthRepository, val spcdeInfoService: SpcdeInfoService) {

    fun getList(year: Int, month: Int): List<HolidayList> {

        var entity = getMonthEntity(year, month)

        if (entity == null) {
            val itemList = spcdeInfoService.getHoliDeInfo(year, month)
            entity = saveMonthEntity(year, month, itemList)
        }

        return entity.dayList.stream().map { HolidayList(entity, it) }.toList()
    }

    private fun saveMonthEntity(year: Int, month: Int, itemList: List<SpcdeInfo.Item>): HolidayMonthEntity {
        val entity = HolidayMonthEntity(year, month)

        if (itemList.isNotEmpty()) {
            itemList.forEach {
                entity.addDayList(HolidayDayEntity(it))
            }
        }

        holidayMonthRepository.save(entity)
        return entity
    }

    private fun getMonthEntity(year: Int, month: Int): HolidayMonthEntity? {
        return holidayMonthRepository.findTopByYearAndMonth(year, month)
    }

}