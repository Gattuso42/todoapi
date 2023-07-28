package com.gattuso.todoapi.service;

import com.gattuso.todoapi.model.Task;
import com.gattuso.todoapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;


    @Override
    public Task getTask(Long id) {
       Optional<Task> task = taskRepository.findTaskById(id);
        return uwrappedData(task);
    }

    @Override
    public List<Task> getAllTask() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task upgradeTask(Task task, Long id) {
        Optional<Task> aux = taskRepository.findTaskById(id);
        Task auxData = uwrappedData(aux);
        auxData.setTitle(task.getTitle());
        auxData.setDescription(task.getDescription());
        auxData.setDueDate(task.getDueDate());
        taskRepository.save(auxData);
        return auxData;
    }

    @Override
    public Task upgradeTaskStatus(Long id,boolean status) {
        Optional<Task> aux = taskRepository.findTaskById(id);
        Task auxData = uwrappedData(aux);
        if(status) auxData.setStatus("completed");
        else auxData.setStatus("pending");
        taskRepository.save(auxData);
        return auxData;
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> aux = taskRepository.findTaskById(id);
        Task auxData = uwrappedData(aux);
        taskRepository.deleteById(id);
    }

    Task uwrappedData(Optional<Task> data) throws EntityNotFoundException {
        if (data.isEmpty()) throw new EntityNotFoundException("This id is not found in the database");
        else return data.get();
    }
}
