package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.constants.ProjectConstants;
import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.model.MeterRecordsByMonths;
import com.example.springbootpostrgremigrationth.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class MeterRecordServiceImpl implements MeterRecordService {

    private MeterRepository repo;

    @Autowired
    public void setRepo(MeterRepository repo) {
        this.repo = repo;
    }

    public Timestamp parseTimestamp(String timestamp){
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

    @Override
    public List<MeterRecord> findMeterRecordsByMonth(int startDate) {
        String monthValueStart;
        if(startDate < 10){
             monthValueStart = "0" + startDate;
        } else monthValueStart = String.valueOf(startDate);
        String startString = "2022-" + monthValueStart + "-01 00:00:00";
        String endString = "2022-" + monthValueStart + "-30 00:00:00";
        Timestamp start = parseTimestamp(startString);
        Timestamp end = parseTimestamp(endString);
        return repo.findAllByTimestampBetween(start, end);
    }

    @Override
    public List<MeterRecordsByMonths> createListOfRecordsByMonths() {
        List<MeterRecordsByMonths> listOfRecordsByMonths = new ArrayList<>();
        MeterRecordsByMonths meterRecordsByMonths;
        for (int i = 1; i <= 12; i++){
            List<MeterRecord> listOfRecordByOneMonth = findMeterRecordsByMonth(i);
            if(listOfRecordByOneMonth.size() != 0) {
                meterRecordsByMonths = new MeterRecordsByMonths();
                meterRecordsByMonths.setListOfRecords(listOfRecordByOneMonth);
                meterRecordsByMonths.setSumOfReadings(findCurrentRecordsSum(listOfRecordByOneMonth));
                listOfRecordsByMonths.add(meterRecordsByMonths);
            }
        }
        return listOfRecordsByMonths;
    }
}
