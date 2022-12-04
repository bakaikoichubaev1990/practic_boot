package com.example.practic_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor

public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_gen")
    @SequenceGenerator(name = "instructor_gen", sequenceName = "instructor_gen", allocationSize = 1)
    private Long id;

    @Column(length = 100000, name = "first_name")
    private String firstName;

    @Column(length = 100000, name = "last_name")
    private String lastName;

    @Column(length = 100000, name = "phone_number")
    private String phoneNumber;

    @Column(length = 100000, unique = true)
    private String email;

    @Column(length = 100000)
    private String specialization;

    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH}, fetch = FetchType.EAGER)
    private Course course;
}