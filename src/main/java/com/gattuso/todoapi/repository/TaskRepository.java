package com.gattuso.todoapi.repository;

import com.gattuso.todoapi.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
