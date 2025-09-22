package baskakov.dev.springboormvc.controller;

import baskakov.dev.springboormvc.model.PetDTO;
import baskakov.dev.springboormvc.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/users/pets")
    public ResponseEntity<PetDTO> createPet(
            @RequestBody PetDTO pet) {
        PetDTO createdPet = petService.createPet(pet);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPet);
    }

    @GetMapping("/users/pets")
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petService.getAllPets());
    }

    @GetMapping("/users/pets/{id}")
    public ResponseEntity<PetDTO> getPetById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petService.getPetById(id));
    }

    @DeleteMapping("/users/pets/{id}")
    public ResponseEntity<Void> deletePetById(
            @PathVariable("id") Long id
    ) {
        petService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/users/pets/{id}")
    public ResponseEntity<PetDTO> updatePetById(
            @PathVariable("id") Long id,
            @RequestBody PetDTO pet
    ) {
        petService.updateById(id, pet);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
