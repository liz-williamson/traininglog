package com.lizw.traininglog.repository;

import com.lizw.traininglog.model.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {
}
