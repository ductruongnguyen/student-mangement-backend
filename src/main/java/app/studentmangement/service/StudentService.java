package app.studentmangement.service;

import app.studentmangement.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listAllStudent();
    void save(Student student);
    void deleteById(Long id);
    Optional<Student> findById(Long id);
    List<Student> findByName(String keyword);
    boolean existByStudentCode(String stdCode);
}
