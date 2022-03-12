package example.springframework.sfgpetclinic.services;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
