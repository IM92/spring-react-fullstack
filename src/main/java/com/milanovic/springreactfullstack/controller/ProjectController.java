package com.milanovic.springreactfullstack.controller;

import com.milanovic.springreactfullstack.domain.Project;
import com.milanovic.springreactfullstack.service.MapValidationService;
import com.milanovic.springreactfullstack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationService validationService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    private ResponseEntity<?> createNewProject(@Valid @RequestBody Project pro, BindingResult result) {
        ResponseEntity<?> errorMap = validationService.getErrorMapValidationService(result);
        if (errorMap != null) return errorMap;
        Project project1 = projectService.saveOrUpdateProject(pro);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }


}
