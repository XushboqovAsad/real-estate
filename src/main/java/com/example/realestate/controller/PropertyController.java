package com.example.realestate.controller;

import com.example.realestate.dto.PropertyDto;
import com.example.realestate.security.oauth.ApplicationUserPrincipal;
import com.example.realestate.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    private static final Logger log = LoggerFactory.getLogger(PropertyController.class);

    @PostMapping
    public ResponseEntity<PropertyDto> create(@RequestBody PropertyDto dto,
                                              @AuthenticationPrincipal ApplicationUserPrincipal user) {
        UUID userId = user.getId();
        PropertyDto created = propertyService.create(dto, userId);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<PropertyDto> getAll(){
        log.info("Fetching all properties");
        return propertyService.getAll();
    }

    @GetMapping("/{id}")
    public PropertyDto getById(@PathVariable UUID id) {
        log.info("Fetching property by id={}", id);
        return propertyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        log.info("Deleting property by id={}", id);
        propertyService.delete(id);
    }
}
