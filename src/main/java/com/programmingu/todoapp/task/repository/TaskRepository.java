package com.programmingu.todoapp.task.repository;

import com.programmingu.todoapp.task.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Optional<Task> findByTitle(String title);
}
