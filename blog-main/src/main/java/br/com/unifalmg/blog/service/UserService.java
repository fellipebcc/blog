package br.com.unifalmg.blog.service;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.InvalidUserException;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id null when fetching for an user.");
        }
        return repository.findById(id).orElseThrow(() ->
                    new UserNotFoundException(
                            String.format("No user found for id %d", id))
                );
    }

    public User add(User user) {
        if (Objects.isNull(user) || Objects.isNull(user.getName())
                || Objects.isNull(user.getUsername()) || Objects.isNull(user.getEmail())) {
            throw new InvalidUserException();
        }
        return repository.save(user);
    }

}
