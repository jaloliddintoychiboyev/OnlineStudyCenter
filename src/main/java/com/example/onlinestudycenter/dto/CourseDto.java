package com.example.onlinestudycenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class CourseDto {
    private String name;
    private  Double price;
    private Integer studycenter_id;
}
