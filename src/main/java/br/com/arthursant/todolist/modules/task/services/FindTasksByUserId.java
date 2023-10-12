package br.com.arthursant.todolist.modules.task.services;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.repositories.TaskRepository;

@Service
public class FindTasksByUserId {

  @Autowired
  private TaskRepository taskRepository;

  public List<Task> findByUserId(UUID userId) {
    return this.taskRepository.findByIdUser(userId);
  }

}
