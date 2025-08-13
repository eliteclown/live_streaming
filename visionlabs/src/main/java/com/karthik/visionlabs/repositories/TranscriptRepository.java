package com.karthik.visionlabs.repositories;

import com.karthik.visionlabs.entities.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript,Long> {
}
