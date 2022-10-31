package com.example.springbootpostrgremigrationth.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@Table(name = "MeterRecords")
public class MeterRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long meterId;

    private String type;

    private String meterGroup;

    private Timestamp timestamp;

    private double currentReading;

}
