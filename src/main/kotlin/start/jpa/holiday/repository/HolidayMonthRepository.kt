package start.jpa.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository
import start.jpa.holiday.model.HolidayMonthEntity

interface HolidayMonthRepository : JpaRepository<HolidayMonthEntity, Int> {

    fun findTopByYearAndMonth(year: Int, month: Int): HolidayMonthEntity?

}
