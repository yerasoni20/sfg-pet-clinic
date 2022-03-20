package example.springframework.sfgpetclinic.repositories;

import example.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastname(String lastname);
}
