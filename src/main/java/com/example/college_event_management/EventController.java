package com.example.college_event_management;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
public Event getEventById(@PathVariable Long id) {
    return eventRepository.findById(id)
            .orElseThrow(() ->
                    new org.springframework.web.server.ResponseStatusException(
                            org.springframework.http.HttpStatus.NOT_FOUND,
                            "Event not found"));
}

@PutMapping("/{id}")
public Event updateEvent(@PathVariable Long id,
                         @RequestBody Event updatedEvent) {

    Event existingEvent = eventRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Event not found"));

    existingEvent.setEventName(updatedEvent.getEventName());
    existingEvent.setEventDate(updatedEvent.getEventDate());
    existingEvent.setVenue(updatedEvent.getVenue());
    existingEvent.setDescription(updatedEvent.getDescription());
    existingEvent.setOrganizer(updatedEvent.getOrganizer());
    existingEvent.setStatus(updatedEvent.getStatus());

    return eventRepository.save(existingEvent);
}

@DeleteMapping("/{id}")
public String deleteEvent(@PathVariable Long id) {

    Event existingEvent = eventRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Event not found"));

    eventRepository.delete(existingEvent);

    return "Event deleted successfully. ID: " + id;
}


    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }
}
