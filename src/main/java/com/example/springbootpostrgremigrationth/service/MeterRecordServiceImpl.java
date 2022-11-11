package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.repository.MeterRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class MeterRecordServiceImpl implements MeterRecordService {

    private final MeterRepository repo;

    public MeterRecordServiceImpl(MeterRepository repo) {
        this.repo = repo;
    }

    @SneakyThrows
    @Override
    public void saveMeterRecord(MeterRecord meterRecord) {
        long id = meterRecord.getMeterId();
        String group = meterRecord.getMeterGroup();
        String type = meterRecord.getType();
        Timestamp time = meterRecord.getTimestamp();
        double rec = meterRecord.getCurrentReading();
        MeterRecord newMeterRecord = new MeterRecord(id, group, type, time, rec);
//        Timestamp time = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(meterRecord.getTimestamp().toString()).getTime());
//        MeterRecord meterRecord1 = new MeterRecord(meterRecord.getMeterId(),
//                                                    meterRecord.getMeterGroup(),
//                                                    meterRecord.getType(),
//                                                    setTimestamp(time),
//                                                    meterRecord.getCurrentReading());
        this.repo.save(newMeterRecord);
    }

    @Override
    public Iterable<MeterRecord> findAllMeterRecords() {
        return repo.findAll();
    }
}
