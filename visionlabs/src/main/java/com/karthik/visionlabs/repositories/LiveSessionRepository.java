package com.karthik.visionlabs.repositories;

import com.karthik.visionlabs.entities.LiveSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveSessionRepository extends JpaRepository<LiveSession,Long> {
    List<LiveSession> findByCourseId(Long courseId);

    List<LiveSession> findByInstructor(String instructor);
}
