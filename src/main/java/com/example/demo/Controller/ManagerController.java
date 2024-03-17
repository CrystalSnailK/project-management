package com.example.demo.Controller;

import com.example.demo.Entities.*;
import com.example.demo.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 86400)
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/accounts/teachers")
    public BaseEntity getAllTeachers() {
        return new BaseEntity(0, "", managerService.getAllTeachers().size(), managerService.getAllTeachers());
    }
    @GetMapping("/accounts/teachers/search")
    public BaseEntity getTeachersByID(@RequestParam("id")Integer id) {
        List<TeachersInformationEntity> ti = managerService.getTeachersByID(id);
        return new BaseEntity(0,"", 1, ti);
    }

    @GetMapping("/research")
    public BaseEntity getResearch(@RequestParam("key") String key){
        System.out.println(key);
        return new BaseEntity(0, "", managerService.getResearch(key).size(), managerService.getResearch(key));
    }




}
