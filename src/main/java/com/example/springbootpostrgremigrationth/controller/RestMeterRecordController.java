package com.example.springbootpostrgremigrationth.controller;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.model.MeterRecordsByMonths;
import com.example.springbootpostrgremigrationth.service.MeterRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class RestMeterRecordController {

    private final MeterRecordService service;

    public RestMeterRecordController(MeterRecordService service) {
        this.service = service;
    }


//    @GetMapping("/authenticated/allRecords")
//    public Iterable<MeterRecord> showAllRecords(Model model) {
//        return service.findAllMeterRecords();
//    }

    @GetMapping("/authenticated/allRecordsByOneMonth") // один request param для нужного месяца, потом parse +1
    public List<MeterRecord> showAllRecordsByOneMonth(@RequestParam(name = "start") int startDate){
        return service.findMeterRecordsByMonth(startDate);
    }

    @GetMapping("/authenticated/allRecordsByAllMonths")
    public List<MeterRecordsByMonths> showAllRecordsByAllMonths(){
        return service.createListOfRecordsByMonths();
    }

//    @PostMapping("/authenticated/saveRecord")
//    public void saveNewRecord(@ModelAttribute("record") @Valid MeterRecord record) {
//        service.saveMeterRecord(record);
//    }

}
