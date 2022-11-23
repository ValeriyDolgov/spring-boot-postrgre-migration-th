package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeterRecordServiceImplTest {

    @Test
    void findCurrentRecordsSum() {
        MeterRecord record = new MeterRecord();
        record.setMeterId(123);
        record.setType("ORP999");
        record.setMeterGroup("room3");
        record.setTimestamp(Timestamp.valueOf("2022-10-10 00:00:00"));
        record.setCurrentReading(189.90);
        List<MeterRecord> recordList = new ArrayList<>();
        recordList.add(record);
        MeterRecordServiceImpl service = new MeterRecordServiceImpl();
        assertEquals(189.9, service.findCurrentRecordsSum(recordList));
        assertNotEquals(178.9, service.findCurrentRecordsSum(recordList));
    }
}