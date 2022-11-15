package com.example.springbootpostrgremigrationth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeterRecordsByMonths {

    public List<MeterRecord> listOfRecords;

    public double sumOfReadings;
}
