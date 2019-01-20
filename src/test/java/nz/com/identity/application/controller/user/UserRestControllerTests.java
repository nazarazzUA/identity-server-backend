package nz.com.identity.application.controller.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRestControllerTests {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUserListTest() throws Exception {
        mvc.perform(
                get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].id").exists())
                .andExpect(jsonPath("$.content[*].created_at").exists())
                .andExpect(jsonPath("$.content[*].updated_at").exists())
                .andExpect(jsonPath("$.content[*].email").exists());
    }

    @Test
    public void createUsersTest() throws Exception {

        JSONObject userPayload = new JSONObject();

        userPayload.put("email", "test_1@gmail.com");
        userPayload.put("password", "passw0rd");
        userPayload.put("password_confirm", "passw0rd");
        userPayload.put("client_id", UUID.randomUUID().toString());

        mvc.perform(
                post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userPayload.toString())
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andExpect(jsonPath("$.email").exists());
    }

}
