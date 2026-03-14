package com.lizw.traininglog.controller;

import com.lizw.traininglog.model.LogEntry;
import com.lizw.traininglog.model.NutritionEntry;
import com.lizw.traininglog.repository.LogEntryRepository;
import com.lizw.traininglog.repository.NutritionEntryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition-entries")
public class NutritionEntryController {

    private final NutritionEntryRepository nutritionEntryRepository;
    private final LogEntryRepository logEntryRepository;

    public NutritionEntryController(NutritionEntryRepository nutritionEntryRepository,
                                    LogEntryRepository logEntryRepository) {
        this.nutritionEntryRepository = nutritionEntryRepository;
        this.logEntryRepository = logEntryRepository;
    }

    @GetMapping
    public List<NutritionEntry> getEntries(@RequestParam(required = false) Long logEntryId) {
        if (logEntryId != null) {
            LogEntry logEntry = logEntryRepository.findById(logEntryId)
                    .orElseThrow(() -> new RuntimeException("Entry not found"));
            return nutritionEntryRepository.findByLogEntry(logEntry);
        }
        return nutritionEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public NutritionEntry getEntryById(@PathVariable Long id) {
        return nutritionEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    @PostMapping
    public NutritionEntry createEntry(@RequestBody NutritionEntry nutritionEntry) {
        return nutritionEntryRepository.save(nutritionEntry);
    }

    @PutMapping("/{id}")
    public NutritionEntry updateEntry(@PathVariable Long id, @RequestBody NutritionEntry updated) {
        if (!nutritionEntryRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        updated.setId(id);
        return nutritionEntryRepository.save(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        nutritionEntryRepository.deleteById(id);
    }

}
