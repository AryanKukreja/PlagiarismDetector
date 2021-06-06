package com.abusyprogrammer.backend.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abusyprogrammer.backend.algorithms.hamming.HammingController;
import com.abusyprogrammer.backend.algorithms.hamming.HammingInput;
import com.abusyprogrammer.backend.algorithms.hamming.HammingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest({ HammingController.class })
public class HammingServiceTests {

	// Mock MVC variable set up
	@Autowired
	private MockMvc mvc;

	@Test
	public void testHammingInput() throws Exception {
		HammingInput input = new HammingInput();
		assertEquals("", input.getText1());
		assertEquals("", input.getText2());
		assertEquals(0, input.getOffset());

		input = new HammingInput("I am eating an apple", "I am devouring an apple");
		assertEquals("I am eating an apple", input.getText1());
		assertEquals("I am devouring an apple", input.getText2());
		assertEquals(0, input.getOffset());

		input = new HammingInput("I am eating an apple", "I am devouring an apple", 5);
		assertEquals("I am eating an apple", input.getText1());
		assertEquals("I am devouring an apple", input.getText2());
		assertEquals(5, input.getOffset());
	}

	@Test
	public void testHammingService() throws Exception {
		HammingService service = new HammingService();
		assertEquals("", service.getLonger());
		assertEquals("", service.getShorter());
		assertEquals(0.0, service.getScore());
		assertEquals(0, service.getMatches());
		assertEquals(-1, service.fullStringCheck());
		assertEquals(-1, service.fullStringCheck(3));
		assertEquals("Error: No strings provided to compare", service.jsonify());

		service = new HammingService("I am going to take a stroll in the park", "I am taking a walk");
		assertEquals("I am going to take a stroll in the park", service.getLonger());
		assertEquals("I am taking a walk", service.getShorter());
		assertEquals(0.0, service.getScore());
		assertEquals(0, service.getMatches());
		assertEquals(0, service.fullStringCheck());
		assertEquals(0, service.fullStringCheck(3));
		assertEquals(-2, service.fullStringCheck(100));

		service = new HammingService("I am taking a walk", "I am going to take a stroll in the park");
		assertEquals("I am going to take a stroll in the park", service.getLonger());
		assertEquals("I am taking a walk", service.getShorter());
		assertEquals(0, service.fullStringCheck(3));
		assertEquals(
				"{\"longer\":\"I am going to take a stroll in the park\",\"shorter\":\"I am taking a walk\",\"score\":0.05128205128205128,\"matches\":2}",
				service.jsonify());
	}

	@Test
	public void testHammingController() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput("I am taking a walk", "I am going to take a stroll in the park", 3)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.longer").value("I am going to take a stroll in the park"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.shorter").value("I am taking a walk"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value(0.05128205128205128))
				.andExpect(MockMvcResultMatchers.jsonPath("$.matches").value(2));

		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput("I am taking a walk", "I am going to take a stroll in the park")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.longer").value("I am going to take a stroll in the park"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.shorter").value("I am taking a walk"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value(0.1794871794871795))
				.andExpect(MockMvcResultMatchers.jsonPath("$.matches").value(7));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}