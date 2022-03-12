package example.springframework.sfgpetclinic.services;

import example.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findByLastName(String lastname);
    Owner findById(Long id);
    Owner save(Owner o);
    Set<Owner> findAll();
}
