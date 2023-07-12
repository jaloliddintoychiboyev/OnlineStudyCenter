package com.example.onlinestudycenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String firstname;
    private  String lastname;
    private  Integer age;
    private Integer course_id;
    private Integer group_id;
}
