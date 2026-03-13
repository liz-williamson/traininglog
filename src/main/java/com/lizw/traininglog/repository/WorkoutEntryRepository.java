package com.lizw.traininglog.repository;

import com.lizw.traininglog.model.WorkoutEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutEntryRepository extends JpaRepository<WorkoutEntry, Long> {
}
