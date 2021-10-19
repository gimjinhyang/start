package start.resource

import org.springframework.web.bind.annotation.*
import start.event.service.EventService
import start.jpa.event.model.EventEntity

@RestController
@RequestMapping("/api/event")
class EventResource(val eventService: EventService) {

    @GetMapping("/list")
    fun getEvents(): List<EventEntity> = eventService.getEvents()

    @PostMapping
    fun postEvent(
        @RequestBody
        event: EventEntity
    ) {
        eventService.save(event)
    }

}