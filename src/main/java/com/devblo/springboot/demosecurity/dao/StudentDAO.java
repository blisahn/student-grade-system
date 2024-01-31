package com.devblo.springboot.demosecurity.dao;

import com.devblo.springboot.demosecurity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO extends JpaRepository<Student,Integer> {

}
