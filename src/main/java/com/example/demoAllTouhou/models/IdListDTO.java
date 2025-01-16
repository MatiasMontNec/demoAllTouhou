package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IdListDTO {
    private List<Long> ids;
}