package com.example.realestate.service;

import com.example.realestate.dto.PropertyDto;
import com.example.realestate.entity.Property;
import com.example.realestate.entity.User;
import com.example.realestate.mapper.PropertyMapper;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public PropertyDto create(PropertyDto dto, UUID userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Property property = PropertyMapper.toEntity(dto, owner);
        propertyRepository.save(property);

        return PropertyMapper.toDto(property);
    }

    public List<PropertyDto> getAll() {
        return propertyRepository.findAll()
                .stream().map(PropertyMapper::toDto)
                .toList();
    }

    public PropertyDto getById(UUID id) {
        return propertyRepository.findById(id)
                .map(PropertyMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public void delete(UUID id) {
        propertyRepository.deleteById(id);
    }
}
