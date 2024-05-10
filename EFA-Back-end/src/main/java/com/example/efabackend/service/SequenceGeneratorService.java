package com.example.efabackend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
 private final JdbcTemplate jdbcTemplate;

    public SequenceGeneratorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int generateSequence(String seqName) {
        String query = "SELECT seq FROM database_sequences WHERE id = ?";
        Integer sequence = jdbcTemplate.queryForObject(query, Integer.class, seqName);
        if (sequence != null) {
            jdbcTemplate.update("UPDATE database_sequences SET seq = seq + 1 WHERE id = ?", seqName);
            return sequence;
        } else {
            jdbcTemplate.update("INSERT INTO database_sequences (id, seq) VALUES (?, ?)", seqName, 1);
            return 1;
        }
    }
}
