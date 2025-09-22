package baskakov.dev.springboormvc.service;

import baskakov.dev.springboormvc.model.PetDTO;
import baskakov.dev.springboormvc.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PetService {
    private Long petId;
    private final Map<Long, PetDTO> pets;
    private final UserService userService;

    public PetService(UserService userService) {
        this.pets = new HashMap<>();
        this.petId = 0L;
        this.userService = userService;
    }


    public PetDTO createPet(PetDTO pet) {
        var newId = ++ petId;
        var newPet = new PetDTO(
                newId,
                pet.getName(),
                pet.getUserId()
        );

        pets.put(newId, newPet);
        Long userId = newPet.getUserId();
        UserDTO currentUser = userService.getUserById(userId);

        if (!currentUser.getId().equals(pet.getUserId())) {
            throw new NoSuchElementException("User with id " + userId + " does not exist");
        }
        userService.addPetToUser(userId, newPet);
        return newPet;
    }

    public List<PetDTO> getAllPets() {
        return  pets.values()
                .stream()
                .toList();
    }

    public PetDTO getPetById(Long id) {
        return Optional.ofNullable(pets.get(id))
                .orElseThrow(() -> new NoSuchElementException("Pet with id " + id + " does not exist"));
    }

    public void deleteById(Long id) {
        var currentPet = pets.get(id);
        var userId = currentPet.getUserId();

        if (currentPet == null) {
            throw new NoSuchElementException("Pet with id " + id + " does not exist");
        }

        var userPets = userService.getUserById(userId).getPets();
        if (!userPets.contains(currentPet)) {
            throw new NoSuchElementException("User with id " + userId + " does not have pets " + currentPet);
        }
        userPets.remove(currentPet);
        pets.remove(id);
    }

    public PetDTO updateById(Long id, PetDTO pet) {
        var currentPet = pets.get(id);
        var userId = currentPet.getUserId();
        if (currentPet == null) {
            throw new NoSuchElementException("User with id " + id + " does not exist");
        }

        var beforeUpdateUserPets = userService.getUserById(userId).getPets();
        beforeUpdateUserPets.remove(currentPet);

        var updatedPet = new PetDTO(
                id,
                pet.getName(),
                pet.getUserId()
        );

        var afterUpdateUserPets = userService
                .getUserById(updatedPet.getUserId()).getPets();
        afterUpdateUserPets.add(updatedPet);

        pets.put(id, updatedPet);
        return updatedPet;

    }
}
