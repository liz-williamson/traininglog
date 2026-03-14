package com.lizw.traininglog.controller;

import com.lizw.traininglog.model.LogEntry;
import com.lizw.traininglog.model.WorkoutEntry;
import com.lizw.traininglog.repository.LogEntryRepository;
import com.lizw.traininglog.repository.WorkoutEntryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-entries")
public class WorkoutEntryController {

    private final WorkoutEntryRepository workoutEntryRepository;
    private final LogEntryRepository logEntryRepository;

    public WorkoutEntryController(WorkoutEntryRepository workoutEntryRepository,
                                  LogEntryRepository logEntryRepository) {
        this.workoutEntryRepository = workoutEntryRepository;
        this.logEntryRepository = logEntryRepository;
    }

    @GetMapping
    public List<WorkoutEntry> getEntries(@RequestParam(required = false) Long logEntryId) {
        if (logEntryId != null) {
            LogEntry logEntry = logEntryRepository.findById(logEntryId)
                    .orElseThrow(() -> new RuntimeException("Entry not found"));
            return workoutEntryRepository.findByLogEntry(logEntry);
        }
        return workoutEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public WorkoutEntry getEntryById(@PathVariable Long id) {
        return workoutEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    @PostMapping
    public WorkoutEntry createEntry(@RequestBody WorkoutEntry workoutEntry) {
        return workoutEntryRepository.save(workoutEntry);
    }

    @PutMapping("/{id}")
    public WorkoutEntry updateEntry(@PathVariable Long id, @RequestBody WorkoutEntry updated) {
        if (!workoutEntryRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        updated.setId(id);
        return workoutEntryRepository.save(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        workoutEntryRepository.deleteById(id);
    }
}
