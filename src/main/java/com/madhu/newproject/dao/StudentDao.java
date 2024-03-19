package com.madhu.newproject.dao;

import com.madhu.newproject.dto.Student;
import com.madhu.newproject.repository.StudentRepo;
import org.hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDao {

    @Autowired
    private StudentRepo studentRepository;

    public Student saveStudent(Student Student) {
        return studentRepository.save(Student);
    }

    public Student getByRollNumber(String rollno) {
        return studentRepository.findByRollNumber(rollno);
    }

    public Student getById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        return optional.orElse(null);

    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void deletStudent(Student Student) {
        studentRepository.delete(Student);
    }

}