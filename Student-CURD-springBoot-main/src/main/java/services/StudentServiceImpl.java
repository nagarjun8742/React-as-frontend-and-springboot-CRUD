package services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.StudentDTO;
import exception.ResourceNotFoundException;
import model.Student;
import repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    private StudentDTO mapToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setCourse(student.getCourse());
        return dto;
    }

    private Student mapToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        return student;
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = mapToEntity(dto);
        return mapToDTO(repo.save(student));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return mapToDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        return mapToDTO(repo.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        repo.delete(student);
    }
}
