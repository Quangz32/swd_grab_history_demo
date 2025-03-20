package com.grab.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryEntry {
    private LocalDateTime time;
    private String message;

    public HistoryEntry(String message) {
        this.time = LocalDateTime.now();
        this.message = message;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("[%s] %s", time.format(formatter), message);
    }
} 