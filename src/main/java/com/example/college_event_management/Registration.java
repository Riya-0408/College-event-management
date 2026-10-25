package com.example.college_event_management;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

import jakarta.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "EVENT_REGISTRATIONS")
public class Registration {

    @Id
    @Column(name = "REGISTRATION_ID")
    private Long registrationId;

    @Column(name = "STUDENT_NAME", nullable = false, length = 100)
    private String studentName;

    @Column(name = "STUDENT_EMAIL", nullable = false, length = 150)
    private String studentEmail;

    @Column(name = "EVENT_ID", nullable = false)
    private Long eventId;

    @CreationTimestamp
    @Column(name = "REGISTRATION_DATE", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    public Registration() {
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
