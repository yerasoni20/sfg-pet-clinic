package example.springframework.sfgpetclinic.controllers;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.model.Pet;
import example.springframework.sfgpetclinic.model.PetType;
import example.springframework.sfgpetclinic.services.PetService;
import example.springframework.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;
        Mockito.when(petService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(
                        Pet.builder()
                                .id(petId)
                                .birthDate(LocalDate.of(2018,11,13))
                                .name("Cutie")
                                .visits(new HashSet<>())
                                .owner(Owner.builder()
                                        .id(ownerId)
                                        .lastname("Doe")
                                        .firstname("Joe")
                                        .build())
                                .petType(PetType.builder()
                                        .name("Dog").build())
                                .build()
                );

        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(visitsUri))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM))
        ;
    }


    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(visitsUri)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date","2018-11-11")
                        .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name(REDIRECT_OWNERS_1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
        ;
    }
}