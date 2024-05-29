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
        String name = pet.getNombre();
        if(name == null || name.isEmpty()){
            throw new ResourceNotFoundException("Pet name required");
        }
        if(nameAlreadyInUse(name)){
            throw new ResourceAlreadyExistExeption("A Pet with that name already exists in the database");
        }

        return iPetRepository.save(pet);
    }

    public Pet getPetById(Long id) throws ResourceNotFoundException {
        Pet pet = iPetRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        return pet;

    }

    public Pet getPetByName(String name) throws ResourceNotFoundException{
       Pet pet = iPetRepository.findByNombre(name).orElse(null);
        if (pet== null) {
            throw new ResourceNotFoundException("Pet not found with id: " + name);
        }
        return pet;
    }

    public List<Pet> getAllPets() {
        return  iPetRepository.findAll();
    }
    private boolean nameAlreadyInUse(String name){
        return iPetRepository.findByNombre(name).isPresent();
    }

    public Pet updatePet(Pet pet) throws ResourceNotFoundException {
        Pet existingPet = iPetRepository.findById(pet.getId()).orElse(null);
        if (existingPet != null) {
            existingPet.setNombre(pet.getNombre());
            existingPet.setEdad(pet.getEdad());
            existingPet.setRaza(pet.getRaza());
            existingPet.setGenero(pet.getGenero());
            existingPet.setTamanio(pet.getTamanio());
            existingPet.setEspecie(pet.getEspecie());
            existingPet.setDescripcion(pet.getDescripcion());
            return iPetRepository.save(existingPet);
        } else {
            throw new ResourceNotFoundException("Pet with ID " + pet.getId() + " not found");
        }
    }

    public void deletePet(Long id) {
        iPetRepository.deleteById(id);
    }
}




