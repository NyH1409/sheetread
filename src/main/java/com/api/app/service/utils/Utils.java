package com.api.app.service.utils;

import com.api.app.model.Student;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.NoArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@NoArgsConstructor
public class Utils {

  public static List<Student> getStudentFromFile(ByteArrayInputStream inputStream) {
    try {
      Workbook workbook = WorkbookFactory.create(inputStream);
      Sheet sheet = workbook.getSheet(workbook.getSheetName(0));
      List<Student> students = new ArrayList<>();
      Iterator<Row> rows = sheet.rowIterator();
      int rowIndex = 0;

      while (rows.hasNext()) {
        Row curretRow = rows.next();
        if (rowIndex == 0) {
          rowIndex++;
        } else {
          Iterator<Cell> cells = curretRow.cellIterator();
          students.add(mapToStudent(cells));
        }
      }

      workbook.close();
      return students;
    } catch (IOException | InvalidFormatException e) {
      throw new RuntimeException(e);
    }
  }

  private static Student mapToStudent(Iterator<Cell> cells) {
    Student student = new Student();
    int index = 0;
    while (cells.hasNext()) {
      Cell currentCell = cells.next();
      if (index == 0) {
        student.setId((long) currentCell.getNumericCellValue());
      } else if (index == 1) {
        student.setName(currentCell.getStringCellValue());
      }
      index++;
    }
    return student;
  }
}
