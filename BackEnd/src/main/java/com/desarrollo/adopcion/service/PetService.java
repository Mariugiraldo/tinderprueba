package com.desarrollo.adopcion.service;
import com.desarrollo.adopcion.exceptions.ResourceAlreadyExistExeption;
import com.desarrollo.adopcion.exceptions.ResourceNotFoundException;
import com.desarrollo.adopcion.modelo.Pet;
import com.desarrollo.adopcion.repository.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private IPetRepository iPetRepository;
    public Pet createPet(Pet pet) throws ResourceNotFoundException, ResourceAlreadyExistExeption {
        if (pet == null) {
            throw new ResourceNotFoundException("Pet object cannot be null");
        }
        if(pet.getName()== null || pet.getName().isEmpty()){
            throw new ResourceNotFoundException("Pet name required");
        }
        if(nameAlreadyInUse(pet.getName())){
            throw new ResourceAlreadyExistExeption("A Pet with that name already exists in the database");
        }

        return iPetRepository.save(pet);
    }

    public Pet getPetById(Integer id) throws ResourceNotFoundException {
        Pet pet = iPetRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        return pet;

    }

    public Pet getPetByName(String name) throws ResourceNotFoundException{
       Pet pet = iPetRepository.findByName(name).orElse(null);
        if (pet== null) {
            throw new ResourceNotFoundException("Pet not found with id: " + name);
        }
        return pet;
    }

    public List<Pet> getAllPets() {
        return  iPetRepository.findAll();
    }
    private boolean nameAlreadyInUse(String name){
        return iPetRepository.findByName(name).isPresent();
    }

    public Pet updatePet(Pet pet) throws ResourceNotFoundException {
        Pet existingPet = iPetRepository.findById(pet.getId()).orElse(null);
        if (existingPet != null) {
            existingPet.setName(pet.getName());
            existingPet.setAge(pet.getAge());
            existingPet.setRace(pet.getRace());
            existingPet.setGender(pet.getGender());
            existingPet.setSize(pet.getSize());
            existingPet.setSpecies(pet.getSpecies());
            existingPet.setDescription(pet.getDescription());
            existingPet.setDate(pet.getDate());
            return iPetRepository.save(existingPet);
        } else {
            throw new ResourceNotFoundException("Pet with ID " + pet.getId() + " not found");
        }
    }

    public void deletePet(Integer id) {
        iPetRepository.deleteById(id);
    }
}




