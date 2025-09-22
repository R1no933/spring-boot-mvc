package baskakov.dev.springboormvc.controller;

import baskakov.dev.springboormvc.model.PetDTO;
import baskakov.dev.springboormvc.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetDTO> createPet(
            @RequestBody @Valid PetDTO pet) {
        PetDTO createdPet = petService.createPet(pet);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPet);
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petService.getPetById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(
            @PathVariable("id") Long id
    ) {
        petService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePetById(
            @PathVariable("id") Long id,
            @RequestBody @Valid PetDTO pet
    ) {
        petService.updateById(id, pet);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
