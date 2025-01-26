package com.example.demoAllTouhou.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MerchListDTO {
    private List<MerchDTO> merch;
}
