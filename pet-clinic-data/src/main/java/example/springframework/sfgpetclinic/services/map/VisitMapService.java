package example.springframework.sfgpetclinic.services.map;

import example.springframework.sfgpetclinic.model.Visit;
import example.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.finsAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit Object) {
        if(Object.getPet()==null || Object.getPet().getOwner()==null || Object.getPet().getOwner().getId()==null
        || Object.getPet().getId()==null)
        {
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(Object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
