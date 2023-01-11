package imc.mscdevopstakeaway22_23;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityFullContainerMockMVCtests {

    @Autowired
    private MockMvc mockMvc;

    // Unit tests using a mock MVC to a secured route - This will fail so test for the failure.
    @Test
    public void addItem() throws Exception {
        this.mockMvc.perform(get("/Admin/AddItem")).andDo(print()).andExpect(status().isFound())
                .andExpect(header().string("Location",  "http://localhost/login"));
    }

    // Unit tests using a mock MVC to a secured route but logged in - this should succeed.
    @Test
    public void addItemLoggedIn() throws Exception {
        this.mockMvc.perform(get("/Admin/AddItem")
                .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Item Page")));
    }

    //  test using a mock MVC but a test database to a secured route - This will fail so test for the failure.
    @Test
    public void deleteItemTest() throws Exception {
        this.mockMvc.perform(get("/Admin/DeleteItem")).andDo(print()).andExpect(status().isFound())
                .andExpect(header().string("Location",  "http://localhost/login"));
    }

    @Test
    public void deleteItemTestLoggedIn() throws Exception {
        this.mockMvc.perform(get("/Admin/DeleteItem")
                .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delete Item Page")));
    }

    //    Tests the post controller for AddItem
    //    and will check it returns the menu page with a repo created response.
    //    This is similar to the Lightweight test but it will test the repo / H2 database access.
    @Test
    @Order(1)
    public void addItemLoggedInPOST() throws Exception {
        this.mockMvc.perform(post("/Admin/AddItem")
                .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "TestMenuItem")
                .param("description", "This is the test to add an Item to the menu")
                .param("price", "1000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestMenuItem")));;
    }

    //    Tests the post controller for DeleteItem
    //    and will check it returns the DeleteItem page with a repo created response.
    //    This is similar to the Lightweight test but it will test the repo / H2 database access.
    @Test
    @Order(2)
    public void deleteItemLoggedInPOST() throws Exception {
        this.mockMvc.perform(post("/Admin/DeleteItem/3")
                .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))
                .with(csrf()))
                .andDo(print());
        this.mockMvc.perform(get("/Menu"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("TestMenuItem"))));
    }

    //    Tests the post controller for DeleteItem to check that items are present
    @Test
    public void deleteItemTestForContentLoggedIn() throws Exception {
        this.mockMvc.perform(get("/Admin/DeleteItem")
                .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("mockDBChips")));
    }

}
