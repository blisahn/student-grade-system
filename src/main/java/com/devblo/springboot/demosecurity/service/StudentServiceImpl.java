package com.devblo.springboot.demosecurity.service;

import com.devblo.springboot.demosecurity.dao.StudentDAO;
import com.devblo.springboot.demosecurity.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student findById(int id) {
        Optional<Student> studentOptional = studentDAO.findById(id);
        return studentOptional.orElse(null);
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void delete(Student student) {
        studentDAO.delete(student);
    }


}
