package com.gattuso.todoapi.service;

import com.gattuso.todoapi.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



public interface TaskService {


    Task getTask(Long id);
    List<Task> getAllTask();
    void saveTask(Task task);
    Task upgradeTask(Task task,Long id);
    Task upgradeTaskStatus(Long id,boolean status);
    void deleteTask(Long id);



}
