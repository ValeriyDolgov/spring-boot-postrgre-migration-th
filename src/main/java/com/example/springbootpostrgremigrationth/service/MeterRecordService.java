package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.MeterRecord;


public interface MeterRecordService {

    void saveMeterRecord(MeterRecord meterRecord);

    Iterable<MeterRecord> findAllMeterRecords();
}
