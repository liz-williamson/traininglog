package com.lizw.traininglog.controller;

import com.lizw.traininglog.model.LogEntry;
import com.lizw.traininglog.model.User;
import com.lizw.traininglog.repository.LogEntryRepository;
import com.lizw.traininglog.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log-entries")
public class LogEntryController {

    private final LogEntryRepository logEntryRepository;
    private final UserRepository userRepository;

    public LogEntryController(LogEntryRepository logEntryRepository,
                              UserRepository userRepository) {
        this.logEntryRepository = logEntryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<LogEntry> getEntries(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Entry not found"));
            return logEntryRepository.findByUser(user);
        }
        return logEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public LogEntry getEntryById(@PathVariable Long id) {
        return logEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    @PostMapping
    public LogEntry createEntry(@RequestBody LogEntry logEntry) {
        return logEntryRepository.save(logEntry);
    }

    @PutMapping("/{id}")
    public LogEntry updateEntry(@PathVariable Long id, @RequestBody LogEntry updated) {
        if (!logEntryRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        updated.setId(id);
        return logEntryRepository.save(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        logEntryRepository.deleteById(id);
    }

}
