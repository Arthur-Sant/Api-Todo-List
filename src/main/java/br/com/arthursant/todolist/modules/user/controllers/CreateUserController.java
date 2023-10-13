package br.com.arthursant.todolist.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.arthursant.todolist.modules.user.entities.User;
import br.com.arthursant.todolist.modules.user.repositories.UserRepository;
import br.com.arthursant.todolist.modules.user.services.CreateUserService;
import br.com.arthursant.todolist.shared.exceptions.AppErrorException;

@RestController
public class CreateUserController {

  @Autowired
  private CreateUserService userService;

  @PostMapping("/users")
  public ResponseEntity create(@RequestBody User user) throws AppErrorException {

    userService.create(user);

    return ResponseEntity.status(HttpStatus.CREATED).body("");
  }
}
