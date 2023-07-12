package com.example.onlinestudycenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private  String   name;
    private Integer student_count;
    private Integer studycenter_id;
}
