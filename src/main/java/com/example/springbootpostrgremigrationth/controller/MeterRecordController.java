package com.example.springbootpostrgremigrationth.controller;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.service.MeterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class MeterRecordController {

    private MeterRecordService service;

    public MeterRecordController(MeterRecordService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("listOfRecords", service.findAllMeterRecords());
        return "index.html";
    }

    @GetMapping("/showNewForm")
    public String newRecord(Model model){
        MeterRecord record = new MeterRecord();
        model.addAttribute("record", record);
        return "new_record.html";
    }

    @PostMapping("/saveRecord")
    public String saveNewRecord(@ModelAttribute("record") MeterRecord record){
        service.saveMeterRecord(record);
        return "redirect:/";
    }

}
