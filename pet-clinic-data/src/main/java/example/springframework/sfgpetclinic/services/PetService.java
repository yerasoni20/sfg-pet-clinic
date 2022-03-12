package example.springframework.sfgpetclinic.services;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet p);
    Set<Pet> findAll();
}
