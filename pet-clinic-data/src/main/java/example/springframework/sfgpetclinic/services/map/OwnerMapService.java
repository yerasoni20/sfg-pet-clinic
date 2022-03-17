package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Pet;
import example.springframework.sfgpetclinic.services.CrudService;
import example.springframework.sfgpetclinic.services.OwnerService;
import example.springframework.sfgpetclinic.services.PetService;
import example.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.finsAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastname) {
        return null;
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

        if(Object!=null)
        {
            if(Object.getPets()!=null)
            {
                Object.getPets().forEach(pet -> {
                    if(pet.getPetType()!=null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }else
                            {
                                throw new RuntimeException();
                            }

                            if(pet.getId()==null)
                            {
                                Pet savedPet=petService.save(pet);
                                pet.setId(savedPet.getId());
                            }
            });
            }
            return super.save(Object);
        }else
        {
            return null;
        }
        }
    }