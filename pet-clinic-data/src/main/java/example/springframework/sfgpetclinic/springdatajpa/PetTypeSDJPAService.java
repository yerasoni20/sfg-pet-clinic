package example.springframework.sfgpetclinic.springdatajpa;

import example.springframework.sfgpetclinic.model.PetType;
import example.springframework.sfgpetclinic.repositories.PetTypeRepository;
import example.springframework.sfgpetclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;

public class PetTypeSDJPAService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJPAService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes=new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType Object) {
        return petTypeRepository.save(Object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
