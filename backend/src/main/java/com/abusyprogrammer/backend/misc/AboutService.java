package com.abusyprogrammer.backend.misc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The About Service implements a REST interface for returning miscellaneous 
 * information about the backend server to the user
 * 
 * @author  Aryan Kukreja
 * @version 1.0
 * @since   2021-03-27
 */
@RestController
@RequestMapping(path = "api/about")
public class AboutService {
    
    public String about() {
        return 
        "{" + 
            "\"name\": \"Plagiarism Detector Web Application\"," + 
            "\"about\": \"A RESTful web-application that provides different string-matching algorithms as endpoints for users to experiment with.\"," + 
            "\"tool\": \"Spring Boot\"" + 
        "}";
    }

}