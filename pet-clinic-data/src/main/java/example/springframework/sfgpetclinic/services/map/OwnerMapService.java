package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.services.CrudService;

import java.util.Set;

public class OwnerMapService extends AbstractMapService<Owner,Long> implements CrudService<Owner,Long> {
    @Override
    public Set<Owner> findAll() {
        return super.finsAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner Object) {
        return super.save(Object.getId(),Object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }
}
