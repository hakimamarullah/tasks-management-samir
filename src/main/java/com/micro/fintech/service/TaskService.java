package com.micro.fintech.service;
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
import org.springframework.data.domain.Pageable;

public interface TaskService {

    ApiResponse<PagedResponse<Task>> getTasks(Long userId, Pageable pageable);
    ApiResponse<PagedResponse<Task>> getIncompleteTasks(Long userId, Pageable pageable);

    ApiResponse<Integer> updateTaskToCompleted(Long userId, Long taskId);

    ApiResponse<Task> createTask(TaskDTO taskDTO);


}
