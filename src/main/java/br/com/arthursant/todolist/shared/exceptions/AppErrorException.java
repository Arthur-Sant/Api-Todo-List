package br.com.arthursant.todolist.shared.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AppErrorException extends Exception {
  private HttpStatus status;
  private String message;

  public AppErrorException(HttpStatus status, String message) {
    super();

    this.status = status;
    this.message = message;
  }
}
