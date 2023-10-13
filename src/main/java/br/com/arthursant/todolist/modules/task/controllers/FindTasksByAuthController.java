package br.com.arthursant.todolist.modules.task.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthursant.todolist.modules.task.services.FindTasksByUserId;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FindTasksByAuthController {

  @Autowired
  private FindTasksByUserId taskService;

  @GetMapping("/tasksByAuth")
  public ResponseEntity findTasksByAuth(HttpServletRequest request) {
    var userId = request.getAttribute("userId");

    var tasks = taskService.findByUserId((UUID) userId);

    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }
}
