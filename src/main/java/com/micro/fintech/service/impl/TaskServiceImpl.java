package com.micro.fintech.service.impl;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 2:52 PM
@Last Modified 5/31/2024 2:52 PM
Version 1.0
*/

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.fintech.model.Task;
import com.micro.fintech.model.User;
import com.micro.fintech.model.dto.PagedResponse;
import com.micro.fintech.model.dto.TaskDTO;
import com.micro.fintech.model.response.ApiResponse;
import com.micro.fintech.repository.TaskRepository;
import com.micro.fintech.repository.UserRepository;
import com.micro.fintech.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ObjectMapper objectMapper,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse<PagedResponse<Task>> getTasks(Long userId, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllTasks(userId, pageable);
        ApiResponse<PagedResponse<Task>> response = ApiResponse.setSuccess("success get all task");

        // Set Task List data
        response.setData(objectMapper.convertValue(tasks, new TypeReference<>() {}));

        return response;
    }

    @Override
    public ApiResponse<PagedResponse<Task>> getIncompleteTasks(Long userId, Pageable pageable) {
        Page<Task> tasks = taskRepository.findIncompleteTasks(userId, pageable);
        ApiResponse<PagedResponse<Task>> response = ApiResponse.setSuccess("success get all incomplete task");

        // Set Task List data
        response.setData(objectMapper.convertValue(tasks, new TypeReference<>() {}));
        return response;
    }

    @Override
    public ApiResponse<Integer> updateTaskToCompleted(Long userId, Long taskId) {
        int rowAffected = taskRepository.setCompletedTrueById(taskId, userId);
        ApiResponse<Integer> response = ApiResponse.setSuccess("success update task to completed");
        response.setData(rowAffected);
        return response;
    }

    @Override
    public ApiResponse<Task> createTask(TaskDTO taskDTO) {
        Task task = objectMapper.convertValue(taskDTO, Task.class);
        Optional<User> user = userRepository.findById(taskDTO.getUserId());

        if (user.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("user id not found while creating a task");
        }
        task.setUser(user.get());
        return ApiResponse.<Task>builder()
                .code(201)
                .message("success created task")
                .data(taskRepository.save(task))
                .build();
    }
}
