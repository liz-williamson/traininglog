package com.lizw.traininglog.controller;

import com.lizw.traininglog.model.LogEntry;
import com.lizw.traininglog.model.WellbeingEntry;
import com.lizw.traininglog.repository.LogEntryRepository;
import com.lizw.traininglog.repository.WellbeingEntryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wellbeing-entries")
public class WellbeingEntryController {

    private final WellbeingEntryRepository wellbeingEntryRepository;
    private final LogEntryRepository logEntryRepository;

    public WellbeingEntryController(WellbeingEntryRepository wellbeingEntryRepository,
                                    LogEntryRepository logEntryRepository) {
        this.wellbeingEntryRepository = wellbeingEntryRepository;
        this.logEntryRepository = logEntryRepository;
    }

    @GetMapping
    public List<WellbeingEntry> getEntries(@RequestParam(required = false) Long logEntryId) {
        if (logEntryId != null) {
            LogEntry logEntry = logEntryRepository.findById(logEntryId)
                    .orElseThrow(() -> new RuntimeException("Entry not found"));
            return wellbeingEntryRepository.findByLogEntry(logEntry);
        }
        return wellbeingEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public WellbeingEntry getEntryById(@PathVariable Long id) {
        return wellbeingEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    @PostMapping
    public WellbeingEntry createEntry(@RequestBody WellbeingEntry wellbeingEntry) {
        return wellbeingEntryRepository.save(wellbeingEntry);
    }

    @PutMapping("/{id}")
    public WellbeingEntry updateEntry(@PathVariable Long id, @RequestBody WellbeingEntry updated) {
        if (!wellbeingEntryRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        updated.setId(id);
        return wellbeingEntryRepository.save(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        wellbeingEntryRepository.deleteById(id);
    }

}
