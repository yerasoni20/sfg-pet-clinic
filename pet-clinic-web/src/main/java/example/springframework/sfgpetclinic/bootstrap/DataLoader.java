package example.springframework.sfgpetclinic.bootstrap;

import example.springframework.sfgpetclinic.model.*;
import example.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService, VisitService visitService)
    {
        this.ownerService=ownerService;
        this.vetService=vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }
    @Override
    public void run(String... args) throws Exception {

        int count=petTypeService.findAll().size();
        if(count==0) {
            loadData();
        }
    }

    private void loadData() {
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


        Owner owner2=new Owner();
        owner2.setFirstname("Fiona");
        owner2.setLastname("Glenanne");
        owner2.setAddress("35 Kinemster");
        owner2.setCity("Florida");
        owner2.setTelephone("235644667");

        System.out.println("Loaded Owners");

        Speciality radiology=new Speciality();
        radiology.setDescription("RadioLogy");
        Speciality savedRadiology=specialitiesService.save(radiology);

        Speciality surgery=new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery=specialitiesService.save(surgery);

        Speciality Dentistry=new Speciality();
        Dentistry.setDescription("Dentistry");
        Speciality savedDentistry=specialitiesService.save(Dentistry);

        Pet mikes=new Pet();
        mikes.setPetType(savedDogPetType);
        mikes.setOwner(owner1);
        mikes.setBirthdate(LocalDate.now());
        mikes.setName("Rosco");
        owner1.getPets().add(mikes);
        ownerService.save(owner1);

        Pet fionascat=new Pet();
        fionascat.setPetType(savedCatPetType);
        fionascat.setOwner(owner2);
        fionascat.setBirthdate(LocalDate.now());
        fionascat.setName("Heliy");
        owner2.getPets().add(fionascat);
        ownerService.save(owner2);

        Visit fionascatvisit=new Visit();
        fionascatvisit.setPet(fionascat);
        fionascatvisit.setDescription("Sneezy Kitty");
        fionascatvisit.setLocalDate(LocalDate.now());

        visitService.save(fionascatvisit);

        Vet vet1=new Vet();
        vet1.setFirstname("Sam");
        vet1.setLastname("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstname("Jessie");
        vet2.setLastname("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets");
    }
}
