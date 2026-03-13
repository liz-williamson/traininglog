package com.lizw.traininglog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "race_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "log_entry_id", nullable = false)
    private LogEntry logEntry;

    @Column(nullable = false)
    private String event;

    @Column(nullable = false)
    private String raceType;

    @Column(nullable = false)
    private BigDecimal distance;

    @Column(nullable = false)
    private String finishTime;

    @Column(nullable = false)
    private Boolean isPr;

    private Short place;

    private Character priority;

    private String notes;
}
