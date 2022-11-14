package com.example.springbootpostrgremigrationth.repository;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MeterRepository extends JpaRepository<MeterRecord, Long> {
    List<MeterRecord> findAllByTimestampBetween(Timestamp start, Timestamp end);
}
