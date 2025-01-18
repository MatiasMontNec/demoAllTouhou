package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercancyDTO {
    private Long id;
    private Long characterId;
    private String name;
    private String description;
    private Integer price;
    private DateDTO updateDate;
    private String link;
}
