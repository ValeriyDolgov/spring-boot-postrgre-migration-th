package com.example.springbootpostrgremigrationth.controller;

import com.example.springbootpostrgremigrationth.model.MeterRecord;
import com.example.springbootpostrgremigrationth.model.MeterRecordsByMonths;
import com.example.springbootpostrgremigrationth.service.MeterRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/authenticated/allRecordsByOneMonth") // один request param для нужного месяца, потом parse +1
    public String showAllRecordsByOneMonth(Model model, @RequestParam(name = "start") int startDate){
        List<MeterRecord> recordList = service.findMeterRecordsByMonth(startDate);
        model.addAttribute("listOfRecordByMonth", recordList);
        model.addAttribute("result", service.findCurrentRecordsSum(recordList));
        return "records_by_month";
    }

    @GetMapping("/authenticated/allRecordsByAllMonths")
    public String showAllRecordsByAllMonths(Model model){
        List<MeterRecordsByMonths> recordList = service.createListOfRecordsByMonths();
        model.addAttribute("listOfRecordByMonths", recordList);
        return "records_by_all_months";
    }

    @GetMapping("/authenticated/showNewForm")
    public String newRecord(Model model) {
        MeterRecord record = new MeterRecord();
        model.addAttribute("record", record);
        return "new_record";
    }

    @PostMapping("/authenticated/saveRecord")
    public String saveNewRecord(@ModelAttribute("record") @Valid MeterRecord record) {
        service.saveMeterRecord(record);
        return "redirect:/";
    }

}
