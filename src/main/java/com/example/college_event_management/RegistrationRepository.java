package com.example.college_event_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository
        extends JpaRepository<Registration, Long> {
}
