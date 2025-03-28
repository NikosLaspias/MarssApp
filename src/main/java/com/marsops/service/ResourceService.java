package com.marsops.service;

import com.marsops.model.Resource;
import com.marsops.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public List<Resource> getAllResources() {
        return repository.findAll();
    }

    public Resource getResourceById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public Resource createResource(Resource resource) {
        return repository.save(resource);
    }

    public Resource updateResource(Long id, Resource updatedResource) {
        Resource existingResource = getResourceById(id);
        existingResource.setName(updatedResource.getName());
        existingResource.setQuantity(updatedResource.getQuantity());
        return repository.save(existingResource);
    }

    public void deleteResource(Long id) {
        repository.deleteById(id);
    }
}
