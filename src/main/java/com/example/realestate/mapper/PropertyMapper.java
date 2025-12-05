package com.example.realestate.mapper;

import com.example.realestate.dto.PropertyDto;
import com.example.realestate.entity.Property;
import com.example.realestate.entity.User;

public class PropertyMapper {

    public static PropertyDto toDto(Property p){
        return PropertyDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .price(p.getPrice())
                .type(p.getType())
                .category(p.getCategory())
                .address(p.getAddress())
                .area(p.getArea())
                .rooms(p.getRooms())
                .ownerId(p.getOwner().getId())
                .build();

    }

    public static Property toEntity(PropertyDto dto, User owner){
        return Property.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .type(dto.getType())
                .category(dto.getCategory())
                .address(dto.getAddress())
                .area(dto.getArea())
                .rooms(dto.getRooms())
                .owner(owner)
                .build();
    }
}
