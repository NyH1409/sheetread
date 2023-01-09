package com.api.app.controller;

import com.api.app.model.Student;
import com.api.app.service.StudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StudentController {
  private final StudentService service;

  @PostMapping(value = "/students/upload")
  public List<Student> uploadStudentFile(@RequestBody byte[] fileToUpload) {
    return service.createStudent(fileToUpload);
  }
}
