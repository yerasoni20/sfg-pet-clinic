package example.springframework.sfgpetclinic.springdatajpa;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.repositories.OwnerRepository;
import example.springframework.sfgpetclinic.repositories.PetRepository;
import example.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    public static final String SMITH = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJPAService ownerSDJPAService;

    Owner returnOwner;
    @BeforeEach
    void setUp() {
        returnOwner=Owner.builder().id(1l).lastname(SMITH).build();
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet=new HashSet<>();
        ownerSet.add(Owner.builder().id(1l).build());
        ownerSet.add(Owner.builder().id(2l).build());

        Mockito.when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> owners=ownerSDJPAService.findAll();

        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner=ownerSDJPAService.findById(1l);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Owner owner=ownerSDJPAService.findById(1l);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner owner=Owner.builder().id(1l).build();
        Mockito.when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(owner);
        Owner savedOwner=ownerSDJPAService.save(owner);
        assertNotNull(savedOwner);
        Mockito.verify(ownerRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        ownerSDJPAService.delete(returnOwner);
        Mockito.verify(ownerRepository,Mockito.times(1)).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        ownerSDJPAService.deleteById(1l);
        Mockito.verify(ownerRepository,Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findByLastName() {
        Owner returnowner=Owner.builder().id(1L).lastname(SMITH).build();
        Mockito.when(ownerRepository.findByLastname(ArgumentMatchers.any())).thenReturn(returnowner);
        Owner smith=ownerSDJPAService.findByLastName(SMITH);
        assertEquals(SMITH,smith.getLastname());
        Mockito.verify(ownerRepository).findByLastname(ArgumentMatchers.any());
    }
}