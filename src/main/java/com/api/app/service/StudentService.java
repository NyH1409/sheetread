package com.api.app.service;

import com.api.app.model.Student;
import com.api.app.repository.StudentRepository;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.api.app.service.utils.Utils.getStudentFromFile;

@Service
@AllArgsConstructor
public class StudentService {
  private final StudentRepository repository;

  public List<Student> createStudent(byte[] toUpload) {
    List<Student> studentFomFile = getStudentFromFile(new ByteArrayInputStream(toUpload));
    return repository.saveAll(studentFomFile);
  }
}
