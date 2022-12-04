package com.example.practic_boot.service.impl;

import com.example.practic_boot.model.Course;
import com.example.practic_boot.model.Instructor;
import com.example.practic_boot.repository.CourseRepository;
import com.example.practic_boot.repository.InstructorRepository;
import com.example.practic_boot.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Autowired

    public InstructorServiceImpl(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Instructor> getInstructorList() {
        return instructorRepository.findAll();
    }

    @Override
    public List<Instructor> getAlInstructors(Long courseId) {
       return instructorRepository.findAllInstructorByCourseId(courseId);

    }

    @Override
    public void addInstructor(Long id, Instructor instructor) {
        Course course = courseRepository.getById(id);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        instructorRepository.save(instructor);

    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getById(id);
    }

    @Override
    public void updateInstructor(Instructor instructor, Long id) {
        Instructor instructor1 = instructorRepository.getById(id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructorRepository.save(instructor1);

    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);

    }

    @Override
    public void assignInstructor(Long courseId, Long instructorId) throws IOException {
        Instructor instructor = instructorRepository.getById(instructorId);
        Course course = courseRepository.getById(courseId);
        if (course.getInstructorList()!=null){
            for (Instructor g : course.getInstructorList()) {
                if (g.getId() == instructorId) {
                    throw new IOException("This instructor already exists!");
                }
            }
        }
        instructor.getCourse().getInstructorList().remove(instructor);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        courseRepository.save(course);
    }
}
