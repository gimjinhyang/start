package start.event.service

import org.springframework.stereotype.Service
import start.jpa.event.model.EventEntity
import start.jpa.event.repository.EventRepository

@Service
class EventService(val eventRepository: EventRepository) {

    fun getEvents(): List<EventEntity> {
        return eventRepository.findAll()
    }

    fun save(event: EventEntity) {
        eventRepository.save(event)
    }


}