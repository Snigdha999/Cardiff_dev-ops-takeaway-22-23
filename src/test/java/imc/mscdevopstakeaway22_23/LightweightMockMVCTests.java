package imc.mscdevopstakeaway22_23;


import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
import imc.mscdevopstakeaway22_23.controllers.GeneralController;

import imc.mscdevopstakeaway22_23.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneralController.class)
@Import(WebSecurityConfig.class)
public class LightweightMockMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepository ItemRepo;


    // Simple Unit tests using a mockMVC - no change here from container testing as no access to database
    @Test
    public void a_testGreeting() throws Exception {
        this.mockMvc.perform(get("/Home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("HomePage")));
    }

    //  Component? test using a mockMVC but mocking the repository response (Note the @MockBean)
    //  This only tests the controller and the view layers
    @Test
    public void getMenuTest() throws Exception {

        ItemDTO item = new ItemDTO("mockDBChips", "desc", 3,2);

        // This will return what the ItemRepo would have returned - a List of ItemDTOs
        given(this.ItemRepo.getAllItems()).willReturn(Arrays.asList(item));

        this.mockMvc.perform(get("/Menu")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("mockDBChips")));
    }

}
