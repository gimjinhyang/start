package start.redis.holiday.repository;

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import start.redis.holiday.model.HolidayMonthHash

@Repository
interface HolidayMonthRedisRepository : CrudRepository<HolidayMonthHash, String> {

}
