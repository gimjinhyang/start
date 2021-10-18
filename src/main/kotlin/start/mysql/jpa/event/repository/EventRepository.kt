package start.mysql.jpa.event.repository;

import org.springframework.data.jpa.repository.JpaRepository
import start.mysql.jpa.event.model.EventEntity

interface EventRepository : JpaRepository<EventEntity, Int> {

}
