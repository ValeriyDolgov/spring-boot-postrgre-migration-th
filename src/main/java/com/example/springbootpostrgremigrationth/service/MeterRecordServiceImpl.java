package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.constants.ProjectConstants;
import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.repository.MeterRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;


@Service
public class MeterRecordServiceImpl implements MeterRecordService {

    private final MeterRepository repo;

    public MeterRecordServiceImpl(MeterRepository repo) {
        this.repo = repo;
    }

    private Timestamp parseTimestamp(String timestamp){
        try{
            return new Timestamp(ProjectConstants.TIMESTAMP_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public double findCurrentRecordsSum(List<MeterRecord> recordList){
        double result = 0;
        for (MeterRecord meterRecord : recordList) {
            result += meterRecord.getCurrentReading();
        }
        return result;
    }

    @Override
    public void saveMeterRecord(MeterRecord meterRecord) {
        this.repo.save(meterRecord);
    }

    @Override
    public Iterable<MeterRecord> findAllMeterRecords() {
        return repo.findAll();
    }

    @Override//здесь надо описать так, end = start + 1
    public List<MeterRecord> findMeterRecordsByMonth(int startDate) {
        String monthValueStart;
        if(startDate < 10){
             monthValueStart = "0" + startDate;
        } else monthValueStart = String.valueOf(startDate);
        String monthValueEnd = String.valueOf(Integer.valueOf(monthValueStart + 1));
        if(startDate == 12) {
            monthValueEnd = "01";
        }
        String startString = "2022-" + monthValueStart + "-01 00:00:00";
        String endString = "2022-" + monthValueEnd + "-01 00:00:00";
        Timestamp start = parseTimestamp(startString);
        Timestamp end = parseTimestamp(endString);
        return repo.findAllByTimestampBetween(start, end);
    }
}
