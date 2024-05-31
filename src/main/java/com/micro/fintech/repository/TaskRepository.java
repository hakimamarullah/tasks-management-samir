package com.micro.fintech.repository;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 11:38 AM
@Last Modified 5/31/2024 11:38 AM
Version 1.0
*/

import com.micro.fintech.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1", nativeQuery = true)
    Page<Task> findAllTasks(Long userId, Pageable pageable);

    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1 AND completed = false", nativeQuery = true)
    Page<Task> findIncompleteTasks(Long userId, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "UPDATE tasks SET completed = true WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    int setCompletedTrueById(Long taskId, Long userId);
}
