package com.example.demoAllTouhou.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchDTO {
    private Long id;
    private Long characterId;
    private String name;
    private String description;
    private Integer price;
    private DateDTO updateDate;
    private String link;
}
