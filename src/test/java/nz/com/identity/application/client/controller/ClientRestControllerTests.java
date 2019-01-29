package nz.com.identity.application.client.controller;

import nz.com.identity.application.IntegrationTestsBase;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientRestControllerTests extends IntegrationTestsBase {

    @Test()
    public void createClientTest() throws Exception {

        JSONObject clientPayload = new JSONObject();
        clientPayload.put("client_id", UUID.randomUUID().toString());
        clientPayload.put("client_type", "confidential");
        clientPayload.put("allowed_domain", "test.com");
        clientPayload.put("allowed_redirect_urls", "redirect_to");
        clientPayload.put("allowed_client_ips", "127.0.0.1");
        clientPayload.put("access_token_ttl", 60);
        clientPayload.put("refresh_token_ttl", 3600);

        mvc.perform(
                post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientPayload.toString())
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.client_id").exists())
                .andExpect(jsonPath("$.client_secret").doesNotExist())
                .andExpect(jsonPath("$.client_type").exists())
                .andExpect(jsonPath("$.allowed_domain").exists())
                .andExpect(jsonPath("$.allowed_redirect_urls").exists())
                .andExpect(jsonPath("$.allowed_client_ips").exists())
                .andExpect(jsonPath("$.access_token_ttl").value(60))
                .andExpect(jsonPath("$.refresh_token_ttl").value(3600))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists());
    }
}
