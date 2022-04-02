package example.springframework.sfgpetclinic.controllers;

import example.springframework.sfgpetclinic.model.Owner;
import example.springframework.sfgpetclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        owners=new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());

        mockMvc= MockMvcBuilders.standaloneSetup(ownerController).build();
;    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyZeroInteractions(ownerService);
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyZeroInteractions(ownerService);
    }
    @Test
    void displayOwner() throws Exception {
        Mockito.when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
                .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.hasProperty("id", Matchers.is(1l))));

    }

    @Test
    void processFindFormReturnMany() throws Exception {
        Mockito.when(ownerService.findAllByLastnameLike(ArgumentMatchers.anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                        Owner.builder().id(2l).build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attribute("selections", Matchers.hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        Mockito.when(ownerService.findAllByLastnameLike(ArgumentMatchers.anyString())).thenReturn(Arrays.asList(Owner.builder().id(1l).build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));
    }

    @Test
    void processCreationForm() throws Exception {
        Mockito.when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        Mockito.when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyZeroInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        Mockito.when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(ownerService).save(ArgumentMatchers.any());
    }
}