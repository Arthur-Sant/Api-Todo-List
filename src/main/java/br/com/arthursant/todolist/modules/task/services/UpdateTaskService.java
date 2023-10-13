package br.com.arthursant.todolist.modules.task.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.repositories.TaskRepository;
import br.com.arthursant.todolist.shared.utils.PropertiesUtil;

@Service
public class UpdateTaskService {

  @Autowired
  private TaskRepository taskRepository;

  public Task updateTask(Task task, UUID id) throws Exception {

    var taskExists = this.taskRepository.findById(id)
        .orElseThrow(() -> new Exception("Task não encontrada"));

    PropertiesUtil.copyNonNullPropertiesUtil(task, taskExists);

    return this.taskRepository.save(taskExists);
  }
}
