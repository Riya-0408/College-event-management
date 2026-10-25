package com.example.college_event_management;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final JdbcTemplate jdbcTemplate;

    public RegistrationController(
            RegistrationRepository registrationRepository,
            JdbcTemplate jdbcTemplate) {
        this.registrationRepository = registrationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
public List<Registration> getRegistrations() {
    System.out.println("REGISTERED ROWS: "
            + registrationRepository.count());

    return registrationRepository.findAll();
}
    @PostMapping
    public ResponseEntity<?> registerStudent(
            @RequestBody Registration registration) {

        if (registration.getStudentName() == null
                || registration.getStudentName().isBlank()
                || registration.getStudentEmail() == null
                || registration.getStudentEmail().isBlank()
                || registration.getEventId() == null) {

            return ResponseEntity.badRequest()
                    .body("Student name, email, and event ID are required.");
        }

        try {
            Long nextId = jdbcTemplate.queryForObject(
                    "SELECT REGISTRATION_SEQ.NEXTVAL FROM DUAL",
                    Long.class);

            registration.setRegistrationId(nextId);
            registration.setRegistrationDate(null);

            Registration saved =
                    registrationRepository.save(registration);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(
                    "Registration failed. Check that the event exists "
                    + "and this email has not already registered for it.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegistration(
            @PathVariable Long id,
            @RequestBody Registration updatedRegistration) {

        if (updatedRegistration.getStudentName() == null
                || updatedRegistration.getStudentName().isBlank()
                || updatedRegistration.getStudentEmail() == null
                || updatedRegistration.getStudentEmail().isBlank()
                || updatedRegistration.getEventId() == null) {

            return ResponseEntity.badRequest().body(
                    "Student name, email, and event ID are required.");
        }

        try {
            Registration existing = registrationRepository
                    .findById(id)
                    .orElse(null);

            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Registration not found.");
            }

            existing.setStudentName(
                    updatedRegistration.getStudentName().trim());

            existing.setStudentEmail(
                    updatedRegistration.getStudentEmail().trim());

            existing.setEventId(updatedRegistration.getEventId());

            Registration saved =
                    registrationRepository.save(existing);

            return ResponseEntity.ok(saved);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(
                    "Update failed. Check that the event exists "
                    + "and this email is not already registered.");
        }
    }

       @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegistration(
            @PathVariable Long id) {

        if (!registrationRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Registration not found.");
        }

        registrationRepository.deleteById(id);

        return ResponseEntity.ok(
                "Registration deleted successfully.");
    }
}
