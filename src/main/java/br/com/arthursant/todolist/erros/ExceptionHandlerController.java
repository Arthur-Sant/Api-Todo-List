package br.com.arthursant.todolist.erros;

import org.apache.catalina.connector.Response;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.arthursant.todolist.shared.exceptions.AppErrorException;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMostSpecificCause().getMessage());
  }

  @ExceptionHandler(AppErrorException.class)
  public ResponseEntity<String> handleAppError(AppErrorException exception) {
    return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
  }
}
