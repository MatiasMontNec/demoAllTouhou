package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateDTO {
    private int year;
    private int month;
    private int day;
}
