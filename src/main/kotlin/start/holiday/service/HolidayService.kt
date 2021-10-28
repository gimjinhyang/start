package start.holiday.service

import org.springframework.stereotype.Service
import start.holiday.model.HolidayList
import start.jpa.holiday.model.HolidayMonthEntity
import start.jpa.holiday.repository.HolidayMonthJpaRepository
import start.openapi.datagokr.model.SpcdeInfoItem
import start.openapi.datagokr.service.SpcdeInfoService
import start.redis.holiday.model.HolidayMonthHash
import start.redis.holiday.repository.HolidayMonthRedisRepository
import java.util.*

@Service
class HolidayService(
    val holidayMonthJpaRepository: HolidayMonthJpaRepository,
    val holidayMonthRedisRepository: HolidayMonthRedisRepository,
    val spcdeInfoService: SpcdeInfoService
) {

    fun getList(year: Int, month: Int): List<HolidayList> {
        val hash = getMonthHash(year, month)
        if (hash.isPresent) {
            return hash.get().dayList.map { HolidayList(hash.get(), it) }.toList()
        }

        val entity = getMonthEntity(year, month)
        if (entity.isPresent) {
            saveMonthHash(entity.get())
            return entity.get().dayList.map { HolidayList(entity.get(), it) }.toList()
        }

        val itemList = spcdeInfoService.getHoliDeInfo(year, month)
        saveMonthEntity(year, month, itemList)
        return itemList.map { HolidayList(it) }.toList()
    }

    private fun saveMonthEntity(year: Int, month: Int, itemList: MutableList<SpcdeInfoItem>) {
        val entity = HolidayMonthEntity(year, month, itemList)
        holidayMonthJpaRepository.save(entity)
    }

    private fun saveMonthHash(entity: HolidayMonthEntity) {
        val hash = HolidayMonthHash(entity)
        holidayMonthRedisRepository.save(hash)
    }

    private fun getMonthHash(year: Int, month: Int): Optional<HolidayMonthHash> {
        return holidayMonthRedisRepository.findById("$year$month")
    }

    private fun getMonthEntity(year: Int, month: Int): Optional<HolidayMonthEntity> {
        return holidayMonthJpaRepository.findTopByYearAndMonth(year, month)
    }

}