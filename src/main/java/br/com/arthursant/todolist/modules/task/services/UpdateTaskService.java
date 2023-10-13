package br.com.arthursant.todolist.modules.task.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.repositories.TaskRepository;
import br.com.arthursant.todolist.shared.exceptions.AppErrorException;
import br.com.arthursant.todolist.shared.utils.PropertiesUtil;

@Service
public class UpdateTaskService {

  @Autowired
  private TaskRepository taskRepository;

  public Task updateTask(Task task, UUID id) throws AppErrorException {

    var taskExists = this.taskRepository.findById(id)
        .orElseThrow(() -> new AppErrorException(HttpStatus.NOT_FOUND, "Task não encontrada"));

    PropertiesUtil.copyNonNullPropertiesUtil(task, taskExists);

    return this.taskRepository.save(taskExists);
  }
}
