package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MeterRecordServiceImpl implements MeterRecordService{

    private final MeterRepository repo;

    public MeterRecordServiceImpl(MeterRepository repo) {
        this.repo = repo;
    }

    @Override
    public void saveMeterRecord(MeterRecord meterRecord) {
        this.repo.save(meterRecord);
    }

    public List<MeterRecord> findAllMeterRecords(){
        return repo.findAll();
    }
}
