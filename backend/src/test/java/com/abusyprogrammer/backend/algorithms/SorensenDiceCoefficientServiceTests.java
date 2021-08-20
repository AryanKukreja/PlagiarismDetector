package com.abusyprogrammer.backend.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abusyprogrammer.backend.algorithms.SorensenDiceCoefficient.SorensenDiceCoefficientController;
import com.abusyprogrammer.backend.algorithms.SorensenDiceCoefficient.SorensenDiceCoefficientInput;
import com.abusyprogrammer.backend.algorithms.SorensenDiceCoefficient.SorensenDiceCoefficientService;
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
@WebMvcTest({ SorensenDiceCoefficientController.class })
public class SorensenDiceCoefficientServiceTests {

	// Mock MVC variable set up
	@Autowired
	private MockMvc mvc;

	private final String TEXT_1 = "I am taking a walk";
	private final String TEXT_2 = "I am going to take a stroll in the park";

	@Test
	public void testSorensenDiceCoefficientInput() throws Exception {
		SorensenDiceCoefficientInput input = new SorensenDiceCoefficientInput(TEXT_1, TEXT_2);
		assertEquals(TEXT_1, input.getText1());
		assertEquals(TEXT_2, input.getText2());
	}

	@Test
	public void testSorensenDiceCoefficientService() throws Exception {
		SorensenDiceCoefficientService service = new SorensenDiceCoefficientService(TEXT_1, TEXT_2);
		assertEquals(0.0, service.getScore());
		assertEquals(TEXT_1, service.getText1());
		assertEquals(TEXT_2, service.getText2());
	}

	@Test
	public void testSorensenDiceCoefficientController() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/sorensen-dice-coefficient/")
				.content(asJsonString(new SorensenDiceCoefficientInput("", ""))).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		mvc.perform(MockMvcRequestBuilders.post("/api/sorensen-dice-coefficient/")
				.content(asJsonString(new SorensenDiceCoefficientInput(TEXT_1, ""))).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		mvc.perform(MockMvcRequestBuilders.post("/api/sorensen-dice-coefficient/")
				.content(asJsonString(new SorensenDiceCoefficientInput("", TEXT_2))).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		mvc.perform(
				MockMvcRequestBuilders.post("/api/sorensen-dice-coefficient/").content(asJsonString(new SorensenDiceCoefficientInput(TEXT_1, TEXT_2)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.text1").value(TEXT_1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.text2").value(TEXT_2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value(0.4));

		mvc.perform(
				MockMvcRequestBuilders.post("/api/sorensen-dice-coefficient/").content(asJsonString(new SorensenDiceCoefficientInput("AA", "AAAAAA")))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.text1").value("AA"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.text2").value("AAAAAA"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value((double) 1 / (double) 3));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}