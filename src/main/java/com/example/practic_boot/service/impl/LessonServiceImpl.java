package com.example.practic_boot.service.impl;

import com.example.practic_boot.model.Course;
import com.example.practic_boot.model.Lesson;
import com.example.practic_boot.repository.CourseRepository;
import com.example.practic_boot.repository.LessonRepository;
import com.example.practic_boot.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public List<Lesson> getAllLessons(Long id) {
        return lessonRepository.findAllLessonByCourseId(id);
    }

    @Override
    public void addLesson(Long id, Lesson lesson) {
        Course course = courseRepository.getById(id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getById(id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        Lesson lesson1 = lessonRepository.findById(id).get();
        lesson1.setLessonName(lesson.getLessonName());
        lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lessonRepository.delete(lesson);
    }
}
