package start.jpa.holiday.model

import javax.persistence.*

@Table(name = "holiday_month", schema = "test")
@Entity
class HolidayMonthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_month_id", nullable = false, updatable = false)
    var holidayMonthId: Int? = null

    @Column(name = "year", updatable = false)
    val year: Int

    @Column(name = "month", updatable = false)
    val month: Int

    @OneToMany(mappedBy = "month", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val dayList: MutableList<HolidayDayEntity> = mutableListOf()


    constructor(year: Int, month: Int) {
        this.year = year
        this.month = month
    }

    fun addDayList(day: HolidayDayEntity) {
        day.month = this
        this.dayList.add(day)
    }


}