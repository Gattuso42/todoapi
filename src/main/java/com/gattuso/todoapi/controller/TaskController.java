package com.gattuso.todoapi.controller;


import com.gattuso.todoapi.model.Task;
import com.gattuso.todoapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.gattuso.todoapi.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Task Controller",description = "Create,Retrieves,Update and Delete Task")
@RequestMapping("/todoapi")
public class TaskController {

    private TaskService taskService;

//  Read a task
    @Operation(summary = "Get task by id",description = "Get a task using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successful retrieval of task",content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404",description = "Task doesn't exist",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/task/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id),HttpStatus.OK);
    }
//  Read all tasks
    @Operation(summary = "Get all contacts",description ="Retrieves a list of contacts" )
    @ApiResponse(responseCode = "200",description = "List retrieved successfully")
    @GetMapping(value = "task/all",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Task>> getAllTask() {
        return new ResponseEntity<>(taskService.getAllTask(),HttpStatus.OK);
    }
//  Create a task
    @Operation(summary = "Save a task",description = "Allows user creating and saving a task")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201",description = "The task was saved successfully"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    @PostMapping(value = "/task")
    ResponseEntity<Task> saveTask(@Valid @RequestBody Task task) {
        taskService.saveTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//  Upgrade a task

    @Operation(summary ="Update a Task",description = "Allows user upgrading the features of a task")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "The Task was upgraded successfully",content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404",description = "The Task doesn't exist",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or Client Bad Request",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping(value = "/task/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Task> upgradeTask(@Valid @RequestBody Task task,@PathVariable Long id) {
        return new ResponseEntity<>(taskService.upgradeTask(task,id),HttpStatus.OK);
    }
//  Upgrade a status task

    @Operation(summary = "Update a Task status",description = "Receive a id and status (boolean true='completed' or false='pending') and update the Task status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Task status was updated successfully",content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404",description = "Task doesn't exist",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or Client Bad Request",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping(value = "/task/status/{id}/{status}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Task> upgradeTaskStatus(@PathVariable Long id,@PathVariable boolean status) {
        return new ResponseEntity<>(taskService.upgradeTaskStatus(id,status),HttpStatus.OK);
    }
//  Delete a task
    @Operation(summary = "Deleted by id",description = "Delete a task using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "The task was deleted successfully"),
            @ApiResponse(responseCode = "404",description = "The task doesn't exist",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping(value = "/task/{id}")
    ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Test Endpoint
    @Operation(summary = "Test connection",description = "Endpoint for test API connection")
    @ApiResponse(responseCode = "200",description = "The API is activated")
    @GetMapping(value = "/test")
    ResponseEntity<String>testConnection(){
        return new ResponseEntity<>("API is activated...",HttpStatus.OK);
    }


}
