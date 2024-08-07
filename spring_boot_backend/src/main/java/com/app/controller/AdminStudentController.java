package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Subject;
import com.app.model.User;
import com.app.repository.SubjectRepository;
import com.app.service.SubjectService;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/admin/")
public class AdminStudentController {
    @Autowired
    private UserService userService;
   
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/new/subject")
    public ResponseEntity<Subject> createStudent(@RequestBody Subject subject) {
        return new ResponseEntity<>(subjectService.createStudent(subject), HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        return new ResponseEntity<>(userService.getAllStudents(), HttpStatus.OK);
    }
    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

}

