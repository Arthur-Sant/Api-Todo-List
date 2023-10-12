package br.com.arthursant.todolist.modules.task.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.repositories.TaskRepository;
import br.com.arthursant.todolist.modules.user.entities.User;
import br.com.arthursant.todolist.modules.user.repositories.UserRepository;

@Service
public class CreateTaskService {

  @Autowired
  private TaskRepository taskRepository;

  public void create(Task task) throws Exception {
    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
      throw new Exception("A data de início / término deve ser maior do que a data atual");
    }

    if (task.getStartAt().isAfter(task.getEndAt())) {
      throw new Exception("A data de início deve ser menor do que a data de término");
    }

    this.taskRepository.save(task);

  }
}
