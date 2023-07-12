package com.example.onlinestudycenter.controller;

import com.example.onlinestudycenter.dto.TeacherDto;
import com.example.onlinestudycenter.entity.Course;
import com.example.onlinestudycenter.entity.Group;
import com.example.onlinestudycenter.entity.Teachers;
import com.example.onlinestudycenter.repository.CourseRepository;
import com.example.onlinestudycenter.repository.GroupRepository;
import com.example.onlinestudycenter.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    GroupRepository groupRepository;

    @GetMapping(value = "/teacher")
    public List<Teachers> teachersList() {
        return teacherRepository.findAll();
    }

    @GetMapping(value = "/teacher/{id}")
    public Teachers getTeacher(@PathVariable Integer id) {
        Teachers teachers = teacherRepository.findById(id).get();
        return teachers;
    }

    @PostMapping(value = "/teacher")
    public String addTeacher(@RequestBody TeacherDto teacherDto) {
        Course course = courseRepository.findById(teacherDto.getCourse_id()).get();
        Group group = groupRepository.findById(teacherDto.getGroup_id()).get();
        Teachers teachers = new Teachers();
        teachers.setFirstname(teacherDto.getFirstname());
        teachers.setLastname(teacherDto.getLastname());
        teachers.setSalary(course.getPrice() * 0.4 * group.getStudent_count());
        teachers.setCourse(course);
        teachers.setGroup(group);
        teacherRepository.save(teachers);
        return "Teacher Muvaffaqiyatli Qo'shildi!!!";
    }

    @DeleteMapping(value = "/teacher/{id}")
    public String deleteTeacher(@PathVariable Integer id) {
        teacherRepository.deleteById(id);
        return "Teacher Muvaffaqiyatli O'chirildi!!!";
    }

    @PutMapping(value = "/teacher/{id}")
    public String updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Integer id) {
        Course course = courseRepository.findById(teacherDto.getCourse_id()).get();
        Group group = groupRepository.findById(teacherDto.getGroup_id()).get();
        Teachers teachers = new Teachers();
        teachers.setFirstname(teacherDto.getFirstname());
        teachers.setLastname(teacherDto.getLastname());
        teachers.setSalary(group.getStudent_count() * 0.4 * course.getPrice());
        teachers.setCourse(course);
        teachers.setGroup(group);
        teacherRepository.save(teachers);
        return "Teacher Muvaffaqiyatli Update Qilindi!!!";
    }
}
