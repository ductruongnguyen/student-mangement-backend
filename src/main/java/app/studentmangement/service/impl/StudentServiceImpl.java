package app.studentmangement.service.impl;

import app.studentmangement.model.Student;
import app.studentmangement.repository.StudentRepository;
import app.studentmangement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findByName(String keyword) {
        return studentRepository.findByNameContaining(keyword);
    }

    @Override
    public boolean existByStudentCode(String stdCode) {
        return studentRepository.existsStudentByStudentCode(stdCode);
    }
}
