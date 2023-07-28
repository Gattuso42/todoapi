package com.gattuso.todoapi.controller;


import com.gattuso.todoapi.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/todoapi")
public class TaskController {


    @GetMapping("/task/{id}")
    ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("task/all")
    ResponseEntity<Task> getAllTask() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/task")
    ResponseEntity<Task> saveTask(@RequestBody Task task) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/task")
    ResponseEntity<Task> upgradeTask(@RequestBody Task task, Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    ResponseEntity<Task> deleteTask(Long id) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Test Endpoint
    @GetMapping("/test")
    ResponseEntity<String>testConnection(){
        return new ResponseEntity<>("API is activated...",HttpStatus.OK);
    }


}
