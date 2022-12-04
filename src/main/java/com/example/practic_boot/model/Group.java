package com.example.practic_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "group_gen")
    @SequenceGenerator(name = "group_gen",sequenceName = "group_gen",allocationSize = 1)
    private Long id;

    @Column(length = 100000,name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;

    @Column(length = 100000)
    private String image;

    @ManyToMany(cascade = {MERGE,DETACH,REFRESH},fetch = FetchType.LAZY)
    private List<Course> courseList;
    public void addCourse(Course course){
        if (courseList == null){
            courseList=new ArrayList<>();
        }
        courseList.add(course);
    }
    @OneToMany(cascade = {ALL},fetch = FetchType.LAZY,mappedBy = "group")
    private List<Student> studentList;
    public void addStudent(Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
        for (Course c : courseList) {
            c.getCompany().koshuuStudent();


        }
    }
}