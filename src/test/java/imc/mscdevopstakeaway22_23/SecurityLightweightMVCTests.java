package imc.mscdevopstakeaway22_23;


import imc.mscdevopstakeaway22_23.controllers.GeneralController;

import imc.mscdevopstakeaway22_23.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneralController.class)
@Import(WebSecurityConfig.class)
public class SecurityLightweightMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepository ItemRepo;


    @Test
    public void deleteNotLoggedIn() throws Exception {
        this.mockMvc.perform(get("/Admin/DeleteItem")).andDo(print()).andExpect(status().isFound())
                .andExpect(header().string("Location",  "http://localhost/login"));
    }

    @Test
    public void deleteLoggedIn() throws Exception {
        this.mockMvc.perform(get("/Admin/DeleteItem")
                .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delete Item Page")));
    }

}
