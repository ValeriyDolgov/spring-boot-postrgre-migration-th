package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.repository.MeterRepository;
import org.springframework.data.domain.Example;

import java.util.List;

public interface MeterRecordService {

    void saveMeterRecord(MeterRecord meterRecord);

    List<MeterRecord> findAllMeterRecords();
}
