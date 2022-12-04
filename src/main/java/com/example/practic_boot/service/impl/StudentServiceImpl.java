package com.example.practic_boot.service.impl;

import com.example.practic_boot.model.Course;
import com.example.practic_boot.model.Group;
import com.example.practic_boot.model.Student;
import com.example.practic_boot.repository.GroupRepository;
import com.example.practic_boot.repository.StudentRepository;

import com.example.practic_boot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAlStudents(Long id) {
        return studentRepository.findAllStudentByGroupId(id);
    }

    @Override
    public void addStudent(Long id, Student student) throws IOException {
        List<Student> students = studentRepository.findAll();
        for (Student i : students) {
            if (i.getEmail().equals(student.getEmail())) {
                throw new IOException("Student with email already exists!");
            }
        }

        Group group = groupRepository.getById( id);
        group.addStudent(student);
        student.setGroup(group);


        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student student1 = studentRepository.getById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.getById(id);
        for (Course c:student.getGroup().getCourseList()) {
            c.getCompany().kemuuStudent();
        }
        studentRepository.delete(student);
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        Student student = studentRepository.getById(studentId);
        Group group = groupRepository.getById(groupId);

        if (group.getStudentList()!=null){
            for (Student g : group.getStudentList()) {
                if (g.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }

        student.getGroup().getStudentList().remove(student);
        group.addStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
        groupRepository.save(group);
    }
}
