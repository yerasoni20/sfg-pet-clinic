package example.springframework.sfgpetclinic.services;

import example.springframework.sfgpetclinic.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner,Long>{
    Owner findByLastName(String lastname);
    List<Owner> findAllByLastnameLike(String lastname);
}
