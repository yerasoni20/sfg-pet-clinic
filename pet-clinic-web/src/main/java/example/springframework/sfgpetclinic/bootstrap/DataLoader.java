package example.springframework.sfgpetclinic.bootstrap;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Pet;
import example.springframework.sfgpetclinic.model.PetType;
import example.springframework.sfgpetclinic.model.Vet;
import example.springframework.sfgpetclinic.services.OwnerService;
import example.springframework.sfgpetclinic.services.PetTypeService;
import example.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService)
    {
        this.ownerService=ownerService;
        this.vetService=vetService;
        this.petTypeService = petTypeService;
    }
    @Override
    public void run(String... args) throws Exception {

        PetType petType=new PetType();
        petType.setName("dog");
        PetType savedDogPetType=petTypeService.save(petType);

        PetType cat=new PetType();
        cat.setName("cat");
        PetType savedCatPetType=petTypeService.save(cat);

        Owner owner1=new Owner();
        owner1.setFirstname("Michael");
        owner1.setLastname("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("242443333");
        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstname("Fiona");
        owner2.setLastname("Glenanne");
        owner2.setAddress("35 Kinemster");
        owner2.setCity("Florida");
        owner2.setTelephone("235644667");
        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Pet mikes=new Pet();
        mikes.setPetType(savedDogPetType);
        mikes.setOwner(owner1);
        mikes.setBirthdate(LocalDate.now());
        mikes.setName("Rosco");
        owner1.getPets().add(mikes);

        Pet fionascat=new Pet();
        fionascat.setPetType(savedCatPetType);
        fionascat.setOwner(owner2);
        fionascat.setBirthdate(LocalDate.now());
        fionascat.setName("Heliy");
        owner2.getPets().add(fionascat);

        Vet vet1=new Vet();
        vet1.setFirstname("Sam");
        vet1.setLastname("Axe");

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstname("Jessie");
        vet2.setLastname("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets");
    }
}
