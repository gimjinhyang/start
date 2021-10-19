package start.redis.holiday.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import start.jpa.holiday.model.HolidayDayEntity
import start.jpa.holiday.model.HolidayMonthEntity

@RedisHash(value = "holiday_month", timeToLive = 60 * 60 * 24)
class HolidayMonthHash {

    @Id
    val id: String

    val year: Int

    val month: Int

    val dayList: MutableList<Day> = mutableListOf()


    class Day {

        val day: Int

        val name: String


        constructor(entity: HolidayDayEntity) {
            this.day = entity.day
            this.name = entity.name
        }
    }

    constructor(entity: HolidayMonthEntity) {
        this.id = "${entity.year}${entity.month}"
        this.year = entity.year
        this.month = entity.month

        entity.dayList.forEach {
            this.dayList.add(Day(it))
        }
    }


}
