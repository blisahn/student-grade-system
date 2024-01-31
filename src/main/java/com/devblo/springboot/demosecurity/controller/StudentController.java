package com.devblo.springboot.demosecurity.controller;

import com.devblo.springboot.demosecurity.entity.Student;
import com.devblo.springboot.demosecurity.service.StudentService;
import com.devblo.springboot.demosecurity.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student-page")
public class StudentController {

    private final StudentService studentService;

    private boolean shouldProcess = false;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/getStudents")
    public String showStudents(Model model) {
        List<Student> students = studentService.findAll();
        if (this.shouldProcess) {
            for (Student student : students) {
                if (student.letterGrade().equals("DD")) {
                    student.setRight(true);
                }
                studentService.save(student);
            }
        }
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/regisForm")
    public String addStudent(Model model) {
        Student studentToAdd = new Student();
        model.addAttribute("studentToAdd", studentToAdd);
        System.out.println("register form");
        return "student-form";
    }


    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("studentToAdd") Student studentToAdd,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student-form";
        }
        studentService.save(studentToAdd);
        return "redirect:/student-page/getStudents";
    }



    @GetMapping("/showFormForUpdate")
    public String updateForm(@RequestParam("studentId") int studentId, Model model) {
        Student student = studentService.findById(studentId);
        model.addAttribute("studentToAdd", student);
        return "student-form";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int studentId) {
        Student studentToDelete = studentService.findById(studentId);
        studentService.delete(studentToDelete);
        return "redirect:/student-page/getStudents";
    }

    @GetMapping("/processGrades")
    public String processGrades() {
        this.shouldProcess = true;
        return "redirect:/student-page/getStudents";
    }


}
