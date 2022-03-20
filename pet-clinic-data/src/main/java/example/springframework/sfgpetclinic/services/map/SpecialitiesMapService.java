package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Speciality;
import example.springframework.sfgpetclinic.services.SpecialitiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class SpecialitiesMapService extends AbstractMapService<Speciality,Long> implements SpecialitiesService {
    @Override
    public Set<Speciality> findAll() {
        return super.finsAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality Object) {
        return super.save(Object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
