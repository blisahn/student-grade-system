package com.devblo.springboot.demosecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "First name is compulsory")
    private String name;

    @Column(name = "exam_1")
    @Min(value = 0, message = "must be greater or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    private Double exam_1 = 0.0;

    @Column(name = "exam_2")
    @Min(value = 0, message = "must be greater or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    private Double exam_2 = 0.0;

    @Column(name = "exam_f")
    @Min(value = 0, message = "must be greater or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    private Double exam_f = 0.0;

    @Column(name = "exam_MU")
    @Min(value = 0, message = "must be greater or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    private Double exam_MU = 0.0;
    @Column(name = "has_right")
    private Boolean right = false;


    public Student() {
    }

    public Student(int id, String name, Double exam_1, Double exam_2, Double exam_f, Double exam_MU, Boolean right) {
        this.id = id;
        this.name = name;
        this.exam_1 = exam_1;
        this.exam_2 = exam_2;
        this.exam_f = exam_f;
        this.exam_MU = exam_MU;
        this.right = right;
    }

    public Boolean isRight() {
        return right;
    }

    public void setRight(Boolean hasRight) {
        this.right = hasRight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExam_1() {
        return exam_1;
    }

    public void setExam_1(Double exam_1) {
        this.exam_1 = exam_1;
    }

    public Double getExam_2() {
        return exam_2;
    }

    public void setExam_2(Double exam_2) {
        this.exam_2 = exam_2;
    }

    public Double getExam_f() {
        return exam_f;
    }

    public void setExam_f(Double exam_f) {
        this.exam_f = exam_f;
    }

    public Double getExam_MU() {
        return exam_MU;
    }

    public void setExam_MU(Double exam_MU) {
        this.exam_MU = exam_MU;
    }


    public int calculateAverage() {
        if (isRight()){
            return (int) (((0.3) * (getExam_2() + getExam_1())) + (0.4 * getExam_MU()));
        }
        return (int) (((0.3) * (getExam_2() + getExam_1())) + (0.4 * getExam_f()));
    }

    public String letterGrade() {
        int grade = this.calculateAverage();
        if (grade <= 25) {
            return "DD";
        } else if (grade <= 50) {
            return "DC";
        } else if (grade <= 75) {
            return "CD";
        } else {
            return "BB";
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", exam_1=" + exam_1 +
                ", exam_2=" + exam_2 +
                ", exam_f=" + exam_f +
                ", exam_MU=" + exam_MU +
                ", hasRight=" + right +
                '}';
    }
}
