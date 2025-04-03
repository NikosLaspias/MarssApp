package com.marsops.controller;

import com.marsops.model.Resource;
import com.marsops.repository.ResourceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceRepository repository;

    public ResourceController(ResourceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Resource> getAllResources() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        Resource resource = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(repository.save(resource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Resource updatedResource) {
        Resource existingResource = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        existingResource.setName(updatedResource.getName());
        existingResource.setQuantity(updatedResource.getQuantity());
        return ResponseEntity.ok(repository.save(existingResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
