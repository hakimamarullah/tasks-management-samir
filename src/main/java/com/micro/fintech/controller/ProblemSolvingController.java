package com.micro.fintech.controller;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 4:20 PM
@Last Modified 5/31/2024 4:20 PM
Version 1.0
*/

import com.micro.fintech.model.response.ApiResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problem-solving")
public class ProblemSolvingController {

    @PostMapping("/sum-even-numbers")
    public ResponseEntity<ApiResponse<Integer>> sumOfEvenNumbers(@RequestBody @NotNull List<Integer> numbers) {
        int sum = numbers.stream().filter(num -> num % 2 == 0).mapToInt(Integer::intValue).sum();
        ApiResponse<Integer> response = ApiResponse.setSuccess("success sum all even numbers");
        response.setData(sum);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }
}
