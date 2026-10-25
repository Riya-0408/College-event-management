package com.example.college_event_management;
import jakarta.persistence.*;
import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_gen")
@SequenceGenerator(
    name = "event_seq_gen",
    sequenceName = "EVENTS_SEQ",
    allocationSize = 1
)
@Column(name = "EVENT_ID")
private Long eventId;

    @Column(name = "EVENT_NAME", nullable = false, length = 100)
    private String eventName;

    @Temporal(TemporalType.DATE)
    @Column(name = "EVENT_DATE", nullable = false)
    private Date eventDate;

    @Column(name = "VENUE", nullable = false, length = 150)
    private String venue;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "ORGANIZER", length = 100)
    private String organizer;

    @Column(name = "STATUS", length = 20)
    private String status = "UPCOMING";

    public Event() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
