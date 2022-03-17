package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Speciality;
import example.springframework.sfgpetclinic.model.Vet;
import example.springframework.sfgpetclinic.services.CrudService;
import example.springframework.sfgpetclinic.services.SpecialitiesService;
import example.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService

    {
        private final SpecialitiesService specialitiesService;

        public VetMapService(SpecialitiesService specialitiesService) {
            this.specialitiesService = specialitiesService;
        }

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
            if(Object.getSpecialities().size()>0)
            {
                Object.getSpecialities().forEach(speciality -> {
                    if(speciality.getId()==null)
                    {
                        Speciality savedSpecialty=specialitiesService.save(speciality);
                        speciality.setId(savedSpecialty.getId());
                    }
                });
            }
            return super.save(Object);
        }

        @Override
        public Vet findById(Long id) {
            return super.findById(id);
        }
    }
