package com.lizw.traininglog.repository;

import com.lizw.traininglog.model.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
}
