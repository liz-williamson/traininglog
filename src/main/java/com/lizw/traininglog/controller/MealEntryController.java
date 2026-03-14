package com.lizw.traininglog.controller;

import com.lizw.traininglog.model.MealEntry;
import com.lizw.traininglog.model.NutritionEntry;
import com.lizw.traininglog.repository.MealEntryRepository;
import com.lizw.traininglog.repository.NutritionEntryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-entries")
public class MealEntryController {

    private final MealEntryRepository mealEntryRepository;
    private final NutritionEntryRepository nutritionEntryRepository;

    public MealEntryController(MealEntryRepository mealEntryRepository,
                               NutritionEntryRepository nutritionEntryRepository) {
        this.mealEntryRepository = mealEntryRepository;
        this.nutritionEntryRepository = nutritionEntryRepository;
    }

    @GetMapping
    public List<MealEntry> getEntries(@RequestParam(required = false) Long nutritionEntryId) {
        if (nutritionEntryId != null) {
            NutritionEntry nutritionEntry = nutritionEntryRepository.findById(nutritionEntryId)
                    .orElseThrow(() -> new RuntimeException("Entry not found"));
            return mealEntryRepository.findByNutritionEntry(nutritionEntry);
        }
        return mealEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public MealEntry getEntryById(@PathVariable Long id) {
        return mealEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    @PostMapping
    public MealEntry createEntry(@RequestBody MealEntry mealEntry) {
        return mealEntryRepository.save(mealEntry);
    }

    @PutMapping("/{id}")
    public MealEntry updateEntry(@PathVariable Long id, @RequestBody MealEntry updated) {
        if (!mealEntryRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        updated.setId(id);
        return mealEntryRepository.save(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        mealEntryRepository.deleteById(id);
    }

}
