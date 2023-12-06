package br.com.unifalmg.blog.controller;

import br.com.unifalmg.blog.controller.request.UserRequest;
import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    // localhost:8080/api/user/{id}
    // localhost:8080/api/user/2
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

}
