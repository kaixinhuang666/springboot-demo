package com.example.demo.service;

import com.example.demo.dao.Student;
import com.example.demo.dto.StudentDTO;

import java.util.List;

public interface StudentService {

     StudentDTO getStudentById(long id);

     Long addNewStudent(StudentDTO studentDTO);

     void deleteStudentById(long id);

     StudentDTO updateStudentById(long id, String name, String email);

     List<StudentDTO> getStudentsByAges(int maxAge, int minAge);

//     Student save(Student student);
//
//     Student getStudent(Student student);
}
