package br.com.unifalmg.blog.controller;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
public class BlogController {

    private final UserService service;

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/users")
    public String user(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    public String user(User user) {
        return "newuser";
    }

    @PostMapping("/user")
    public String newUser(@ModelAttribute("user") User user) {
        // TODO: Add the new user
        // service.add || service.save
        log.info("Entrou no cadastro de usuário");
        service.add(user);
        return "newuser";
    }

}
