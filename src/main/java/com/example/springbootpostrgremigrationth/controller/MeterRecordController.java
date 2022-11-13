package com.example.springbootpostrgremigrationth.controller;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.service.MeterRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MeterRecordController {

    private final MeterRecordService service;

    public MeterRecordController(MeterRecordService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/authenticated/testAdminPanel")
    public String showTestAdminPanel(){
        return "test_admin_panel";
    }

    @GetMapping("/onlyAdmin")
    public String showPageForOnlyRoleAdmin(){
        return "page_for_admin_role";
    }

    @GetMapping("/authenticated/allRecords")
    public String showAllRecords(Model model) {
        model.addAttribute("listOfAllRecords", service.findAllMeterRecords());
        return "all_records";
    }

    @GetMapping("/authenticated/showNewForm")
    public String newRecord(Model model) {
        MeterRecord record = new MeterRecord();
        model.addAttribute("record", record);
        return "new_record";
    }

    @PostMapping("/authenticated/saveRecord")
    public String saveNewRecord(@ModelAttribute("record") MeterRecord record) {
        service.saveMeterRecord(record);
        return "redirect:/";
    }

}
