package com.micro.fintech.model.dto;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 2:57 PM
@Last Modified 5/31/2024 2:57 PM
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {


    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;


    @NotBlank
    private String title;


    private String description;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private boolean completed;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long userId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @DateTimeFormat
    private LocalDateTime deadline;
}
