package start.jpa.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository
import start.jpa.holiday.model.HolidayMonthEntity
import java.util.*

interface HolidayMonthJpaRepository : JpaRepository<HolidayMonthEntity, Int> {

    fun findTopByYearAndMonth(year: Int, month: Int): Optional<HolidayMonthEntity>

}
