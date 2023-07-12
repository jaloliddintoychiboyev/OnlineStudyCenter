package com.example.onlinestudycenter.controller;

import com.example.onlinestudycenter.dto.CourseDto;
import com.example.onlinestudycenter.entity.Course;
import com.example.onlinestudycenter.entity.StudyCenter;
import com.example.onlinestudycenter.repository.CourseRepository;
import com.example.onlinestudycenter.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudyRepository studyRepository;

    @GetMapping(value = "/course")
    public List<Course> courseList() {
        return courseRepository.findAll();
    }

    @GetMapping(value = "/course/{id}")
    public Course getCourse(@PathVariable Integer id) {
        Course course = courseRepository.findById(id).get();
        return course;
    }

    @PostMapping(value = "/course")
    public String addCourse(@RequestBody CourseDto courseDto) {
        StudyCenter studyCenter = studyRepository.findById(courseDto.getStudycenter_id()).get();
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setPrice(courseDto.getPrice());
        course.setStudyCenter(studyCenter);
        courseRepository.save(course);
        return "Course Muvaffaqiyatli Qo'shildi!!!";
    }

    @DeleteMapping(value = "/course/{id}")
    public String delerteCourse(@PathVariable Integer id) {
        courseRepository.deleteById(id);
        return "Course Muvaffaqiyatli Delete Qilindi!!!";
    }

    @PutMapping(value = "/course/id")
    public String updateCourse(@PathVariable Integer id, @RequestBody CourseDto courseDto) {
        Optional<Course> byId = courseRepository.findById(id);
        Course course = byId.get();
        StudyCenter studyCenter1 = studyRepository.findById(courseDto.getStudycenter_id()).get();
        if (byId.isPresent()) {
        Course course1=new Course();
        course1.setName(courseDto.getName());
        course.setPrice(courseDto.getPrice());
        course.setStudyCenter(studyCenter1);
        courseRepository.save(course);
        return "Course Muvaffaqiyatli Update Qilindi!!!";
        }else {
            return "Error!!!";
        }

    }


}