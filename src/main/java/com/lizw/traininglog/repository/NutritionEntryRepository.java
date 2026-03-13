package com.lizw.traininglog.repository;

import com.lizw.traininglog.model.NutritionEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionEntryRepository extends JpaRepository<NutritionEntry, Long> {
}
