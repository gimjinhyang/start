package start.jpa.event.repository;

import org.springframework.data.jpa.repository.JpaRepository
import start.jpa.event.model.EventEntity

interface EventRepository : JpaRepository<EventEntity, Int> {

}
