package com.madhu.newproject.repository;

import com.madhu.newproject.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {

    public Student findByRollNumber(String rollno);

}