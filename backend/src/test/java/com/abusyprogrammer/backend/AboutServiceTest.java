package com.abusyprogrammer.backend;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abusyprogrammer.backend.misc.AboutService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * A class for implementing unit tests for the About Service
 * using the Mock MVC
 * 
 * @author Aryan Kukreja
 * @version 1.0
 * @since 2021-06-04
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AboutService.class)
public class AboutServiceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testAboutResults() throws Exception {
		mvc.perform( MockMvcRequestBuilders
		.get("/api/misc/about/", 1)
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

}