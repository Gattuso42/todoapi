package com.gattuso.todoapi.controller;


import com.gattuso.todoapi.model.Task;
import com.gattuso.todoapi.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/todoapi")
public class TaskController {

    TaskService taskService;

//  Read a task
    @GetMapping("/task/{id}")
    ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id),HttpStatus.OK);
    }
//  Read all tasks
    @GetMapping("task/all")
    ResponseEntity<List<Task>> getAllTask() {
        return new ResponseEntity<>(taskService.getAllTask(),HttpStatus.OK);
    }
//  Create a task
    @PostMapping("/task")
    ResponseEntity<Task> saveTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//  Upgrade a task
    @PutMapping("/task/{id}")
    ResponseEntity<Task> upgradeTask(@RequestBody Task task,@PathVariable Long id) {
        return new ResponseEntity<>(taskService.upgradeTask(task,id),HttpStatus.OK);
    }
//  Upgrade a status task
    @PutMapping("/task/status/{id}/{status}")
    ResponseEntity<Task> upgradeTaskStatus(Long id,@PathVariable boolean status) {
        return new ResponseEntity<>(taskService.upgradeTaskStatus(id,status),HttpStatus.OK);
    }
//  Delete a task
    @DeleteMapping("/task/{id}")
    ResponseEntity<Task> deleteTask(Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Test Endpoint
    @GetMapping("/test")
    ResponseEntity<String>testConnection(){
        return new ResponseEntity<>("API is activated...",HttpStatus.OK);
    }


}
