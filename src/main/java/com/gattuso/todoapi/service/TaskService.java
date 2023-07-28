package com.gattuso.todoapi.service;

import com.gattuso.todoapi.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {


    Optional<Task> getTask(Long id);
    List<Task> getAllTask();
    void saveTask(Task task);
    Optional<Task> upgradeTask(Task task,Long id);
    void deleteTask(Long id);



}
