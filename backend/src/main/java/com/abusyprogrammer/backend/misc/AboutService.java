package com.abusyprogrammer.backend.misc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The About Service implements a REST interface for returning miscellaneous
 * information about the backend server to the user
 * 
 * @author Aryan Kukreja
 * @version 1.0
 * @since 2021-03-27
 */
@RestController
public class AboutService {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @RequestMapping(path = "api/misc/about/")
    public String about() throws IOException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser();         
        FileReader reader = new FileReader("src/main/resources/system.properties.json");
        Object obj = jsonParser.parse(reader);

        JSONObject employeeList = (JSONObject) obj;
        return employeeList.toString();
    }

}