package com.example.onlinestudycenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudyCenterAdressDto {
    private String name;
    private String city;

    private String district;

    private String street;

    private String home;
}
