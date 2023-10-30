package com.mayana.usersandcars;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mayana.usersandcars.controller.UsersController;

@WebMvcTest(UsersController.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").exists());
    }

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\", \"login\": \"johndoe\", \"password\": \"password\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                .content("{ \"firstName\": \"Alice\", \"lastName\": \"Smith\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
