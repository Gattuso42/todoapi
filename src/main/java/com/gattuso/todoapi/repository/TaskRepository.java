package com.gattuso.todoapi.repository;

import com.gattuso.todoapi.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task,Long> {

    Optional<Task> findTaskById(Long id);
}
