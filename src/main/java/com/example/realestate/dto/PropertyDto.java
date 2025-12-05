package com.example.realestate.dto;

import com.example.realestate.entity.HouseCategory;
import com.example.realestate.entity.PropertyType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDto {
    private UUID id;
    private String title;
    private String description;
    private double price;
    private PropertyType type;
    private HouseCategory category;
    private String address;
    private double area;
    private int rooms;
    private UUID ownerId;
}
