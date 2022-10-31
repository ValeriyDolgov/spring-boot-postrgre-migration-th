package com.example.springbootpostrgremigrationth.repository;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MeterRepository extends JpaRepository<MeterRecord, Long> {
    List<MeterRecord> findAllByTimestamp(Timestamp timestamp);
}
