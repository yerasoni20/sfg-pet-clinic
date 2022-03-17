package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Pet;
import example.springframework.sfgpetclinic.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetMapService extends AbstractMapService<Pet,Long> implements CrudService<Pet,Long>
    {
        @Override
        public Set<Pet> findAll() {
            return super.finsAll();
        }

        @Override
        public void deleteById(Long id) {
            super.deleteById(id);
        }

        @Override
        public void delete(Pet object) {
            super.delete(object);
        }

        @Override
        public Pet save(Pet Object) {
            return super.save(Object);
        }

        @Override
        public Pet findById(Long id) {
            return super.findById(id);
        }
    }
