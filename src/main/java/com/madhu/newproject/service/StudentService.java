package com.madhu.newproject.service;

import com.madhu.newproject.dao.StudentDao;
import com.madhu.newproject.dto.ResponseStructure;
import com.madhu.newproject.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public ResponseEntity<ResponseStructure<Student>> savestudent(Student student) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        Student stu = studentDao.getByRollNumber(student.getRollno());
        if (Objects.isNull(stu)) {
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("student data saved successfully");
            responseStructure.setData(studentDao.saveStudent(student));
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        } else {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("student data already exists");
            responseStructure.setData(studentDao.saveStudent(student));
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<ResponseStructure<Student>> getById(long id) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        Student student = studentDao.getById(id);
        if (Objects.isNull(student)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("student not found by id" + id);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("student data found by id" + id);
            responseStructure.setData(student);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }

    }

    public ResponseEntity<ResponseStructure<Student>> getByRollNumber(String id) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        Student stu = studentDao.getByRollNumber(id);
        if (Objects.isNull(stu)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("student not found by id" + id);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("student data found by id" + id);
            responseStructure.setData(stu);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }

    }

    public ResponseEntity<ResponseStructure<String>> deleteStudent(long id) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        Student student = studentDao.getById(id);
        if (Objects.isNull(student)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Deleted Student Not Success");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }else {
            studentDao.deletStudent(student);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Deleted Student");
            responseStructure.setData("Deleted Successfully");
            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
    }
}