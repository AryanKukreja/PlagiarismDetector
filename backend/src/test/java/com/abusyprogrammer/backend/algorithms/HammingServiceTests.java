package com.abusyprogrammer.backend.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abusyprogrammer.backend.algorithms.Hamming.HammingController;
import com.abusyprogrammer.backend.algorithms.Hamming.HammingInput;
import com.abusyprogrammer.backend.algorithms.Hamming.HammingService;
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

	private final String SHORT_TEXT = "I am taking a walk";
	private final String LONG_TEXT = "I am going to take a stroll in the park";

	@Test
	public void testHammingInput() throws Exception {
		HammingInput input = new HammingInput(SHORT_TEXT, LONG_TEXT);
		assertEquals(SHORT_TEXT, input.getText1());
		assertEquals(LONG_TEXT, input.getText2());
		assertEquals(0, input.getOffset());

		input = new HammingInput(SHORT_TEXT, LONG_TEXT, 5);
		assertEquals(SHORT_TEXT, input.getText1());
		assertEquals(LONG_TEXT, input.getText2());
		assertEquals(5, input.getOffset());
	}

	@Test
	public void testHammingService() throws Exception {
		HammingService service = new HammingService(LONG_TEXT, SHORT_TEXT);
		assertEquals(LONG_TEXT, service.getLonger());
		assertEquals(SHORT_TEXT, service.getShorter());
		assertEquals(0.0, service.getScore());
		assertEquals(0, service.getMatches());
		assertEquals(0, service.fullStringCheck());
		assertEquals(0, service.fullStringCheck(SHORT_TEXT.length() - 1));
		assertEquals(-2, service.fullStringCheck(LONG_TEXT.length() - SHORT_TEXT.length()));

		service = new HammingService(SHORT_TEXT, LONG_TEXT);
		assertEquals(LONG_TEXT, service.getLonger());
		assertEquals(SHORT_TEXT, service.getShorter());
		assertEquals(0, service.fullStringCheck(SHORT_TEXT.length() - 1));
	}

	@Test
	public void testHammingController() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput(SHORT_TEXT, LONG_TEXT, SHORT_TEXT.length() - 1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.longer").value(LONG_TEXT))
				.andExpect(MockMvcResultMatchers.jsonPath("$.shorter").value(SHORT_TEXT))
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value((double) 4 / (double) 39))
				.andExpect(MockMvcResultMatchers.jsonPath("$.matches").value(4));

		mvc.perform(
				MockMvcRequestBuilders.post("/api/hamming/").content(asJsonString(new HammingInput(SHORT_TEXT, LONG_TEXT)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.longer").value(LONG_TEXT))
				.andExpect(MockMvcResultMatchers.jsonPath("$.shorter").value(SHORT_TEXT))
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value((double) 7 / (double) 39))
				.andExpect(MockMvcResultMatchers.jsonPath("$.matches").value(7));

		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/").content(asJsonString(new HammingInput("", LONG_TEXT)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput(SHORT_TEXT, LONG_TEXT, LONG_TEXT.length() - SHORT_TEXT.length())))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.error").value("The offset cannot be larger than the texts passed in."));

		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput(SHORT_TEXT, "", SHORT_TEXT.length())))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		// Invalid offset (passed in for testing)
		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput("", "", 10)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		// Valid offset (passed in for testing)
		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput("", "", 10)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));

		mvc.perform(MockMvcRequestBuilders.post("/api/hamming/")
				.content(asJsonString(new HammingInput("", "")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Cannot run processing with blank strings."));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}