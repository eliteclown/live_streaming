package com.karthik.visionlabs.repositories;

import com.karthik.visionlabs.entities.Bookmarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRepository extends JpaRepository<Bookmarks,Long> {
}
