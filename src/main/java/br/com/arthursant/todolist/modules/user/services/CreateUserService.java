package br.com.arthursant.todolist.modules.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.arthursant.todolist.modules.user.entities.User;
import br.com.arthursant.todolist.modules.user.repositories.UserRepository;

@Service
public class CreateUserService {

  @Autowired
  private UserRepository userRepository;

  public void create(User user) throws Exception {
    var userExists = this.userRepository.findByUsername(user.getUsername());

    if (userExists != null) {
      throw new Exception("Usuário já existe");
    }

    var passwordHashred = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

    user.setPassword(passwordHashred);

    this.userRepository.save(user);
  }
}
