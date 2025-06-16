package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task, Long id) {
        return repository.findById(id).map((updatedTask) -> {
            updatedTask.setTitle(task.getTitle());
            updatedTask.setCompleted(task.isCompleted());
            return repository.save(updatedTask);
        }).orElseGet(() -> repository.save(task));
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
