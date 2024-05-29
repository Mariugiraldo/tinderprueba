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
    public Pet getPetById(@PathVariable Integer id) throws ResourceNotFoundException {
        return petService.getPetById(id);
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{name}")
    public Pet getPetByName(@PathVariable String name) throws ResourceNotFoundException {
        return petService.getPetByName(name);
    }

    @PutMapping
    public Pet updatePet(@RequestBody Pet pet) throws ResourceNotFoundException {
        return petService.updatePet(pet);

    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        petService.deletePet(id);
    }



}
