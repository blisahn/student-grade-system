package com.devblo.springboot.demosecurity.service;

import com.devblo.springboot.demosecurity.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {

    List<Student> findAll();

    Student findById(int id);

    void save(Student student);

    void delete(Student student);


}
