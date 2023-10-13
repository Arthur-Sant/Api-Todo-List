package br.com.arthursant.todolist.modules.task.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.services.UpdateTaskService;
import br.com.arthursant.todolist.shared.exceptions.AppErrorException;

@RestController
public class UpdateTaskController {

  @Autowired
  private UpdateTaskService taskService;

  @PutMapping("/tasks/{id}")
  public ResponseEntity updateTask(
      @RequestBody Task task,
      @PathVariable UUID id) throws AppErrorException {

    var taskUpdated = this.taskService.updateTask(task, id);
    return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
  }
}
