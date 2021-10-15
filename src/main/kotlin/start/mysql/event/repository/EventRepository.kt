package start.mysql.event.repository;

import org.springframework.data.jpa.repository.JpaRepository
import start.mysql.event.model.EventEntity

interface EventRepository : JpaRepository<EventEntity, Int> {


}