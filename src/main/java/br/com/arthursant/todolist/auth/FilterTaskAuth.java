package br.com.arthursant.todolist.auth;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.arthursant.todolist.modules.user.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component anotation que faz com que o spring gerencie aquela classe

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();

    if (servletPath.startsWith("/tasks")) {
      var authorization = request.getHeader("Authorization");

      var authEncoded = authorization.substring("Basic".length()).trim();

      byte[] authDecode = Base64.getDecoder().decode(authEncoded);

      var authString = new String(authDecode);

      String[] credentials = authString.split(":");
      String username = credentials[0];
      String password = credentials[1];

      var user = this.userRepository.findByUsername(username);

      int UNAUTHORIZED_STATUS = 401;

      if (user == null) {
        response.sendError(UNAUTHORIZED_STATUS);
      } else {

        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());

        if (passwordVerify.verified) {
          request.setAttribute("userId", user.getId());

          filterChain.doFilter(request, response);
        } else {
          response.sendError(UNAUTHORIZED_STATUS);
        }
      }
    } else {

      filterChain.doFilter(request, response);

    }
  }
}
