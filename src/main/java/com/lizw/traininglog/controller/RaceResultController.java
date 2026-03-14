package com.lizw.traininglog.controller;

import com.lizw.traininglog.model.LogEntry;
import com.lizw.traininglog.model.RaceResult;
import com.lizw.traininglog.repository.LogEntryRepository;
import com.lizw.traininglog.repository.RaceResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race-results")
public class RaceResultController {

    private final RaceResultRepository raceResultRepository;
    private final LogEntryRepository logEntryRepository;

    public RaceResultController(RaceResultRepository raceResultRepository,
                                LogEntryRepository logEntryRepository) {
        this.raceResultRepository = raceResultRepository;
        this.logEntryRepository = logEntryRepository;
    }

    @GetMapping
    public List<RaceResult> getEntries(@RequestParam(required = false) Long logEntryId) {
        if (logEntryId != null) {
            LogEntry logEntry = logEntryRepository.findById(logEntryId)
                    .orElseThrow(() -> new RuntimeException("Entry not found"));
            return raceResultRepository.findByLogEntry(logEntry);
        }
        return raceResultRepository.findAll();
    }

    @GetMapping("/{id}")
    public RaceResult getEntryById(@PathVariable Long id) {
        return raceResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    @PostMapping
    public RaceResult createEntry(@RequestBody RaceResult raceResult) {
        return raceResultRepository.save(raceResult);
    }

    @PutMapping("/{id}")
    public RaceResult updateEntry(@PathVariable Long id, @RequestBody RaceResult updated) {
        if (!raceResultRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        updated.setId(id);
        return raceResultRepository.save(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        raceResultRepository.deleteById(id);
    }

}
