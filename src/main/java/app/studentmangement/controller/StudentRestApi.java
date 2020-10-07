package app.studentmangement.controller;

import app.studentmangement.model.Student;
import app.studentmangement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/student", produces = "application/json")
@CrossOrigin("*")
public class StudentRestApi {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/list", produces = "application/json")
    public List<Student> showAll() {
        return studentService.listAllStudent();
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
        System.out.println(student);
        boolean check = studentService.existByStudentCode(student.getStudentCode());
        if(check) {
            System.out.println("Code: " + student.getStudentCode() + " is duplicated!");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        studentService.save(student);
        return new ResponseEntity<Student>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if(optionalStudent.isPresent()) {
            studentService.save(student);
            return new ResponseEntity<Student>(HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return new ResponseEntity<Student>(HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public List<Student> searchByName(@PathVariable String keyword) {
        return studentService.findByName(keyword);
    }
}
