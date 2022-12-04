package com.example.practic_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen", sequenceName = "course_gen", allocationSize = 1)
    private Long id;

    @Column(length = 100000, name = "course_name")
    private String courseName;
    private int duration;

    @Column(length = 100000)
    private String description;

    @ManyToOne(cascade = {DETACH,MERGE,PERSIST,REFRESH},fetch = FetchType.EAGER)
    private Company company;

    @ManyToMany(cascade = {MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    private List<Group> groupList;

    public void addGroup(Group group) {
        if (groupList == null) {
            groupList = new ArrayList<>();
        }
        groupList.add(group);
    }

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Instructor> instructorList;

    public void addInstructor(Instructor instructor) {
        if (instructorList == null) {
            instructorList = new ArrayList<>();
        }
        instructorList.add(instructor);
    }

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Lesson> lessonList;

    public void addLesson(Lesson lesson) {
        if (lessonList == null) {
            lessonList = new ArrayList<>();
        }
        lessonList.add(lesson);
    }


}
