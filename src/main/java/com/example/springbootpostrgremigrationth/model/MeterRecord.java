package com.example.springbootpostrgremigrationth.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

//На основании названия класса создает новубю таблицу

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "meter_records") //НЕ УДАЛЯТЬ
public class MeterRecord {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id")
    private long meterId;

    @Column(name = "type", nullable = false)
    @NotBlank
    @Size(min = 5, max = 255)
    private String type;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(name = "meter_group", nullable = false)
    private String meterGroup;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @NotNull
    @Column(name = "current_reading", nullable = false)
    private double currentReading;
}
