package start.mysql.jpa.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository
import start.mysql.jpa.holiday.model.HolidayMonthEntity

interface HolidayMonthRepository : JpaRepository<HolidayMonthEntity, Int> {

    fun findTopByYearAndMonth(year: Int, month: Int): HolidayMonthEntity?

}
