package baskakov.dev.springboormvc.service;

import baskakov.dev.springboormvc.model.PetDTO;
import baskakov.dev.springboormvc.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private Long id;
    private final Map<Long, UserDTO> users;

    public UserService() {
        this.users = new HashMap<>();
        this.id = 0L;
    }

    public UserDTO createUser(UserDTO user) {
        var newId = ++id;
        var newUser = new UserDTO(
                newId,
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getPets()
        );

        users.put(newId, newUser);

        return newUser;
    }

    public List<UserDTO> getAllUsers() {
        return users
                .values()
                .stream()
                .toList();
    }

    public UserDTO getUserById(Long id) {
        return Optional.ofNullable(users.get(id))
                .orElseThrow(() -> new NoSuchElementException("User with id " + id + " does not exist"));
    }

    public void deleteUserById(Long id) {
        var deletedUser = users.remove(id);
        if (deletedUser == null) {
            throw new NoSuchElementException("User with id " + id + " does not exist");
        }
    }


    public UserDTO updateUserById(Long id, UserDTO user) {
        if (users.get(id) == null) {
            throw new NoSuchElementException("User with id " + id + " does not exist");
        }

        var updatedUser = new UserDTO(
                id,
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getPets()
        );

        users.put(id, updatedUser);
        return updatedUser;
    }

    public void addPetToUser(Long userId, PetDTO pet) {
        var currentUser = users.get(userId);
        List<PetDTO> pets = currentUser.getPets();
        pets.add(pet);
    }
}
