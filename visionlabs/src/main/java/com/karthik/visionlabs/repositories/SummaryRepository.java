package com.karthik.visionlabs.repositories;

import com.karthik.visionlabs.entities.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends JpaRepository<Summary,Long> {
}
