package imc.mscdevopstakeaway22_23;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemMockMVCTests {

    @Autowired
    private MockMvc mockMvc;

    // Unit tests using a mock MVC
    @Test
    public void a_testGreeting() throws Exception {
        this.mockMvc.perform(get("/Home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("HomePage")));
    }

    //  test using a mock MVC but a test database
    @Test
    public void getMenuTest() throws Exception {
        this.mockMvc.perform(get("/Menu")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("mockDBPie")));
    }


}

