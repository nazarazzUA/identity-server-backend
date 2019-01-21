package nz.com.identity.application.user.controller;

import nz.com.identity.application.IntegrationTestsBase;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerTests extends IntegrationTestsBase {

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
