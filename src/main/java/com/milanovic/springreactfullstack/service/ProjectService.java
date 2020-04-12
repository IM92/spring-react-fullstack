package com.milanovic.springreactfullstack.service;

import com.milanovic.springreactfullstack.domain.Project;
import com.milanovic.springreactfullstack.exception.ProjectIdException;
import com.milanovic.springreactfullstack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return repository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase());
        }

    }
}
