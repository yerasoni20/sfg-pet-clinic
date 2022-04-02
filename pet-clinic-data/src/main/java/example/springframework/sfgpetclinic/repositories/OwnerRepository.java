package example.springframework.sfgpetclinic.repositories;

import example.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastname(String lastname);
    List<Owner> findAllByLastnameLike(String lastname);
}
