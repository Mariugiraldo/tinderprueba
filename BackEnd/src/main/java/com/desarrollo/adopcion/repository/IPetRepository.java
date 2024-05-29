package com.desarrollo.adopcion.repository;

import com.desarrollo.adopcion.modelo.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByNombre(String name);
}
