package com.lizw.traininglog.repository;

import com.lizw.traininglog.model.WellbeingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WellbeingEntryRepository extends JpaRepository<WellbeingEntry, Long> {
}
