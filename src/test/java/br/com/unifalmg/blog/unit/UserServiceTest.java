package br.com.unifalmg.blog.unit;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser() {
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                        .id(1)
                        .name("Fellipe")
                        .username("felliperey")
                .build()));
        User response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Fellipe", response.getName()),
                () -> assertEquals("felliperey", response.getUsername())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.findById(2));
    }

    @Test
    @DisplayName("#getAllUsers > When there are users > Return a list of users")
    void getAllUsersWhenThereAreUsersReturnAListOfUsers() {
        List<User> users = Arrays.asList(
                User.builder().id(1).name("Fellipe").username("felliperey").build(),
                User.builder().id(2).name("Joao").username("joaosilva").build(),
                User.builder().id(3).name("Maria").username("mariasilva").build(),
                User.builder().id(4).name("Jose").username("joseoliveira").build(),
                User.builder().id(5).name("Ana").username("anapaula").build()
        );
        when(repository.findAll()).thenReturn(users);
        List<User> response = service.getAllUsers();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(5, response.size()),
                () -> assertEquals("Fellipe", response.get(0).getName()),
                () -> assertEquals("Joao", response.get(1).getName()),
                () -> assertEquals("Maria", response.get(2).getName()),
                () -> assertEquals("Jose", response.get(3).getName()),
                () -> assertEquals("Ana", response.get(4).getName())
        );
    }

    @Test
    @DisplayName("#getAllUsers > When there are no users > Return an empty list")
    void getAllUsersWhenThereAreNoUsersReturnAnEmptyList() {
        when(repository.findAll()).thenReturn(List.of());
        List<User> response = service.getAllUsers();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(0, response.size())
        );
    }

    @Test
    @DisplayName("#getAllUsers > When the list is null > throw an exception")
    void getAllUsersWhenTheListIsNullThrowAnException() {
        when(repository.findAll()).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () ->
                service.getAllUsers());
    }
}
