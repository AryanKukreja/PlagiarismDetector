package com.abusyprogrammer.backend;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abusyprogrammer.backend.misc.AboutService;
import com.abusyprogrammer.backend.misc.EndpointsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * A class for implementing unit tests for the services part of the 
 * Misc package using the Mock MVC
 * 
 * @author Aryan Kukreja
 * @version 1.0
 * @since 2021-06-04
 */
@RunWith(SpringRunner.class)
@WebMvcTest({AboutService.class, EndpointsService.class})
public class MiscServiceTests {

	// Mock MVC variable set up
	@Autowired
	private MockMvc mvc;

	/**
	 * The testAboutResults() method is for running a test on the About service
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAboutService() throws Exception {
		mvc.perform( MockMvcRequestBuilders
		.get("/api/misc/about/", 1)
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void testEndpointsService() throws Exception {
		mvc.perform( MockMvcRequestBuilders
		.get("/api/misc/endpoints/", 1)
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.endpoints").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.endpoints[0].name").value("Hamming Distance"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.endpoints[0].path").value("api/hamming/"));
	}

}