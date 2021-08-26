package com.abusyprogrammer.backend.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abusyprogrammer.backend.algorithms.Jaro.JaroController;
import com.abusyprogrammer.backend.algorithms.Jaro.JaroInput;
import com.abusyprogrammer.backend.algorithms.Jaro.JaroService;
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
@WebMvcTest({ JaroController.class })
public class JaroServiceTests {

	// Mock MVC variable set up
	@Autowired
	private MockMvc mvc;

	private final String SHORT_TEXT = "I am taking a walk";
	private final String LONG_TEXT = "I am going to take a stroll in the park";

	@Test
	public void testJaroInput() throws Exception {
		// TODO Input tests here
	}

	@Test
	public void testJaroService() throws Exception {
		// TODO Service tests here
	}

	@Test
	public void testJaroController() throws Exception {
		// TODO MVC Controller tests here
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}