package com.micro.fintech.controller;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 2:48 PM
@Last Modified 5/31/2024 2:48 PM
Version 1.0
*/

import com.micro.fintech.model.Task;
import com.micro.fintech.model.dto.PagedResponse;
import com.micro.fintech.model.dto.TaskDTO;
import com.micro.fintech.model.response.ApiResponse;
import com.micro.fintech.service.TaskService;
import com.micro.fintech.utils.AuthUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/tasks")
@SecurityRequirement(name = "bearerJWT")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Task>> createTask(Principal principal, @RequestBody @Valid TaskDTO taskDTO) {
        taskDTO.setUserId(AuthUtils.getCurrentUserId(principal));
        ApiResponse<Task> response = taskService.createTask(taskDTO);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<PagedResponse<Task>>> getTasks(Principal principal, @ParameterObject Pageable pageable) {
        ApiResponse<PagedResponse<Task>> response = taskService.getTasks(AuthUtils.getCurrentUserId(principal), pageable);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    @GetMapping("/incomplete")
    public ResponseEntity<ApiResponse<PagedResponse<Task>>> getIncompleteTasks(Principal principal, @ParameterObject Pageable pageable) {
        ApiResponse<PagedResponse<Task>> response = taskService.getIncompleteTasks(AuthUtils.getCurrentUserId(principal), pageable);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<ApiResponse<Integer>> setTaskToCompleted(Principal principal, @PathVariable Long taskId) {
        ApiResponse<Integer> response = taskService.updateTaskToCompleted(AuthUtils.getCurrentUserId(principal), taskId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }
}
