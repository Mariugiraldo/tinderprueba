package com.desarrollo.adopcion.repository;

import com.desarrollo.adopcion.modelo.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPetRepository extends JpaRepository<Pet, Integer> {

    @Query("SELECT r FROM Recipe r WHERE r.name = ?1")
    Optional<Pet> findByName(String name);
}
