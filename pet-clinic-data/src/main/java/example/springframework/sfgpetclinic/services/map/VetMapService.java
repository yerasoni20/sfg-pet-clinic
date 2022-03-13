package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Vet;
import example.springframework.sfgpetclinic.services.CrudService;
import example.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService

    {
        @Override
        public Set<Vet> findAll() {
            return super.finsAll();
        }

        @Override
        public void deleteById(Long id) {
            super.deleteById(id);
        }

        @Override
        public void delete(Vet object) {
            super.delete(object);
        }

        @Override
        public Vet save(Vet Object) {
            return super.save(Object.getId(),Object);
        }

        @Override
        public Vet findById(Long id) {
            return super.findById(id);
        }
    }
