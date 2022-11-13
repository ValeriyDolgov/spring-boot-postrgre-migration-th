package com.example.springbootpostrgremigrationth.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

//На основании названия класса создает новубю таблицу

@Entity
@Getter
@Setter
@ToString
@Table(name = "meter_records") //НЕ УДАЛЯТЬ
public class MeterRecord {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id")
    private long meterId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "meter_group", nullable = false)
    private String meterGroup;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Column(name = "current_reading", nullable = false)
    private double currentReading;

    public MeterRecord() {
    }

    public MeterRecord(long meterId, String type, String meterGroup, Timestamp timestamp, double currentReading) {
        this.meterId = meterId;
        this.type = type;
        this.meterGroup = meterGroup;
        this.timestamp = timestamp;
        this.currentReading = currentReading;
    }
}
