package br.com.arthursant.todolist.modules.task.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.arthursant.todolist.modules.task.entities.Task;
import br.com.arthursant.todolist.modules.task.repositories.TaskRepository;
import br.com.arthursant.todolist.shared.exceptions.AppErrorException;

@Service
public class CreateTaskService {

  @Autowired
  private TaskRepository taskRepository;

  public void create(Task task) throws AppErrorException {
    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
      throw new AppErrorException(HttpStatus.NOT_EXTENDED,
          "A data de início / término deve ser maior do que a data atual");
    }

    if (task.getStartAt().isAfter(task.getEndAt())) {
      throw new AppErrorException(HttpStatus.BAD_REQUEST, "A data de início deve ser menor do que a data de término");
    }

    this.taskRepository.save(task);
  }
}
