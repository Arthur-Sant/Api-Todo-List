package br.com.arthursant.todolist.modules.task.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.services.CreateTaskService;
import br.com.arthursant.todolist.shared.exceptions.AppErrorException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CreateTaskController {

  @Autowired
  private CreateTaskService taskService;

  @PostMapping("/tasks")
  public ResponseEntity create(
      @RequestBody Task task,
      HttpServletRequest request) throws AppErrorException {

    var userId = request.getAttribute("userId");

    task.setIdUser((UUID) userId);

    taskService.create(task);

    return ResponseEntity.status(HttpStatus.CREATED).body("");
  }
}
