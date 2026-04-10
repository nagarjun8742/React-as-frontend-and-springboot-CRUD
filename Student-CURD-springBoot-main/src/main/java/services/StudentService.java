package services;

import java.util.List;

import dto.StudentDTO;

public interface StudentService {

    StudentDTO createStudent(StudentDTO dto);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO updateStudent(Long id, StudentDTO dto);

    void deleteStudent(Long id);
}