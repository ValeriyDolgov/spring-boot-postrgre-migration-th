package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.model.MeterRecordsByMonths;

import java.util.List;

public interface MeterRecordService {

    void saveMeterRecord(MeterRecord meterRecord);

    Iterable<MeterRecord> findAllMeterRecords();

    List<MeterRecord> findMeterRecordsByMonth(int startDate);

    double findCurrentRecordsSum(List<MeterRecord> recordList);

    List<MeterRecordsByMonths> createListOfRecordsByMonths();
}
