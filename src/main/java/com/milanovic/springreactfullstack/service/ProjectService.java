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

    public Project findProjectByIdentifier(String projectId) {
        Project project = repository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "does not exist! ");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return repository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = repository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Cannot Project with ID '" + projectId + ". This project does not exist! ");
        }
        repository.delete(project);
    }
}
