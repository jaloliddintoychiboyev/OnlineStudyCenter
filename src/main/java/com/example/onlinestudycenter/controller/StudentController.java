package com.example.onlinestudycenter.controller;

import com.example.onlinestudycenter.dto.StudentDto;
import com.example.onlinestudycenter.entity.Course;
import com.example.onlinestudycenter.entity.Group;
import com.example.onlinestudycenter.entity.Student;
import com.example.onlinestudycenter.repository.CourseRepository;
import com.example.onlinestudycenter.repository.GroupRepository;
import com.example.onlinestudycenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    GroupRepository groupRepository;
    @GetMapping(value = "/student")
    public List<Student> studentList(){
        return studentRepository.findAll();
    }
    @GetMapping(value = "/student/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentRepository.findById(id).get();
    }
    @PostMapping(value = "/student")
    public String addSrtudent(@RequestBody StudentDto studentDto){
        Student student=new Student();
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setAge(studentDto.getAge());
        Group group = groupRepository.findById(studentDto.getGroup_id()).get();
        Course course = courseRepository.findById(studentDto.getCourse_id()).get();
        student.setCourse(course);
        student.setGroup(group);
        student.setPay(course.getPrice());
        studentRepository.save(student);
        return "Student Muvaffaqiyatli Qo'shildi!!!";
    }
    @PutMapping(value = "/student/{id}")
    public String update(@PathVariable Integer id,@RequestBody StudentDto studentDto) {
        Optional<Student> byId = studentRepository.findById(id);
        Student student = byId.get();
        if (byId.isPresent()) {
//            Student student = new Student();
            Course course = courseRepository.findById(studentDto.getCourse_id()).get();
            Group group = groupRepository.findById(studentDto.getGroup_id()).get();
            student.setFirstname(studentDto.getFirstname());
            student.setLastname(studentDto.getLastname());
            student.setAge(studentDto.getAge());
            student.setPay(course.getPrice());
            student.setCourse(course);
            student.setGroup(group);
            studentRepository.save(student);
            return "Student Muvaffaqiyatli Update Qilindi!!!";
        } else {
            return "Error!!!";
        }
    }
    @DeleteMapping(value = "/student/{id}")
    public String delete(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Student Muvaffaqiyatli O'chirildi!!!";
    }
}
