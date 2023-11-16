package com.mayana.usersandcars;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("testCarsController")
public class CarsControllerTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @Test
	    public void testGetCarById() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/1")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.year").exists());
	    }

	    @Test
	    public void testCreateCar() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/cars")
	                .content("{ \"year\": 2020, \"licensePlate\": \"XYZ123\", \"model\": \"Sedan\", \"color\": \"Blue\" }")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.year").value(2020));
	    }

	    @Test
	    public void testUpdateCar() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.put("/api/cars/1")
	                .content("{ \"year\": 2021, \"color\": \"Silver\" }")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.year").value(2021));
	    }

	    @Test
	    public void testDeleteCar() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cars/1")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
}
