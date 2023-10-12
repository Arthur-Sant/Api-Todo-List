package br.com.arthursant.todolist.modules.task.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arthursant.todolist.modules.task.entities.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, UUID> {
  List<Task> findByIdUser(UUID idUser);
}
