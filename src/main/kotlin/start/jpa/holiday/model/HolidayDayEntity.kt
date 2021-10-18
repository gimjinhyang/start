package start.jpa.holiday.model

import start.openapi.datagokr.model.SpcdeInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Table(name = "holiday_day", schema = "test")
@Entity
class HolidayDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_day_id", nullable = false, updatable = false)
    var holidayDayId: Int? = null

    @Column(name = "day", updatable = false)
    val day: Int

    @Column(name = "name", updatable = false)
    val name: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holiday_month_id")
    var month: HolidayMonthEntity? = null


    constructor(item: SpcdeInfo.Item) {
        val date = LocalDate.parse(item.locdate, DateTimeFormatter.BASIC_ISO_DATE)
        this.day = date.dayOfMonth
        this.name = item.dateName
    }


}


