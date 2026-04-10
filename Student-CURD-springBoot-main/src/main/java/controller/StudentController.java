package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dto.StudentDTO;
import services.StudentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;


    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return service.getAllStudents();
    }

    
    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO dto) {
        return service.createStudent(dto);
    }

   
    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO dto) {
        return service.updateStudent(id, dto);
    }

    
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Deleted Successfully";
    }
}