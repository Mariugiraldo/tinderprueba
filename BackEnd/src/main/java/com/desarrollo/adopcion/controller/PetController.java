package com.desarrollo.adopcion.controller;

import com.desarrollo.adopcion.exceptions.ResourceAlreadyExistExeption;
import com.desarrollo.adopcion.exceptions.ResourceNotFoundException;
import com.desarrollo.adopcion.modelo.Pet;
import com.desarrollo.adopcion.repository.IPetRepository;
import com.desarrollo.adopcion.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    public PetService petService;

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) throws ResourceAlreadyExistExeption, ResourceNotFoundException {
        return petService.createPet(pet);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) throws ResourceNotFoundException {
        return petService.getPetById(id);
    }

    @GetMapping
    public List<Pet> getAllPets(@RequestParam(required = false) String name) throws ResourceNotFoundException {
        if(name == null || name.isBlank()) {
            return petService.getAllPets();
        } else {
            return List.of(petService.getPetByName(name));
        }
    }

    @PutMapping
    public Pet updatePet(@RequestBody Pet pet) throws ResourceNotFoundException {
        return petService.updatePet(pet);

    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}
