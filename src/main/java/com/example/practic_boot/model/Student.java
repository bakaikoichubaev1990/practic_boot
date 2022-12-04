package com.example.practic_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_gen", sequenceName = "student_gen", allocationSize = 1)
    private Long id;
    @Column(length = 100000,name = "first_name")
    private String firstName;
    @Column(length = 100000,name = "last_name")
    private String lastName;
    @Column(length = 100000,name = "phone_name")
    private String phoneNumber;
    @Column(length = 100000)
    private String email;
    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {MERGE,DETACH,REFRESH},fetch = FetchType.EAGER)
    private Group group;
}
