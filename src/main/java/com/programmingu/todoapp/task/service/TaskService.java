package com.programmingu.todoapp.task.service;

import com.programmingu.todoapp.task.entity.Task;
import com.programmingu.todoapp.task.exception.BusinessLogicException;
import com.programmingu.todoapp.task.exception.ExceptionCode;
import com.programmingu.todoapp.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task){
        verifyExistsTitle(task.getTitle());
        return taskRepository.save(task);
    }

    public Task updateTask(Task task){
        Task findTask = findVerifiedTask(task.getTaskId());

        Optional.ofNullable(task.getTitle())
                .ifPresent(title -> findTask.setTitle(title));
        Optional.ofNullable(task.getOrder())
                .ifPresent(order ->findTask.setOrder(order));
        Optional.ofNullable(task.isCompleted())
                .ifPresent(completed ->findTask.setCompleted(completed));

        return taskRepository.save(findTask);
    }

    public Task findTask(long taskId){
        return findVerifiedTask(taskId);
    }

    public List<Task> findTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public void deleteTask(long taskId){
        Task findTask = findVerifiedTask(taskId);
        taskRepository.delete(findTask);
    }
    public Task findVerifiedTask(long taskId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Task findTask = optionalTask.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TASK_NOT_FOUND));
        return findTask;
    }
    private void verifyExistsTitle(String title){
        Optional<Task> task = taskRepository.findByTitle(title);
        if(task.isPresent())
            throw new BusinessLogicException(ExceptionCode.TASK_EXISTS);
    }
}
