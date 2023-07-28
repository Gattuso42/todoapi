package com.gattuso.todoapi.service;

import com.gattuso.todoapi.model.Task;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    @Override
    public Optional<Task> getTask(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> getAllTask() {
        return null;
    }

    @Override
    public void saveTask(Task task) {

    }

    @Override
    public Optional<Task> upgradeTask(Task task, Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteTask(Long id) {

    }
}
