package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Pet;
import example.springframework.sfgpetclinic.services.CrudService;
import example.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class PetMapService extends AbstractMapService<Pet,Long> implements PetService
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
