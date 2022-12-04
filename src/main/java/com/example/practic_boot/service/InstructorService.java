package com.example.practic_boot.service;

import com.example.practic_boot.model.Instructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface InstructorService {
    List<Instructor> getInstructorList();
    List<Instructor> getAlInstructors(Long courseId);

    void addInstructor(Long id,Instructor instructor);

    Instructor getInstructorById(Long id);

    void updateInstructor(Instructor instructor, Long id);

    void deleteInstructor(Long id);

    void assignInstructor(Long courseId,Long instructorId) throws IOException;
}
