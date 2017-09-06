package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * This application is designed to explore the Spring Boot framework.
 * 
 * Done: Explore template conventions
 * 
 * Done: Host static resource
 * 
 * Done: Integrate with Heroku and Github
 * 
 * TODO: Inject via application.properties
 *  
 * TODO: Add unit tests
 * 
 * TODO: Add a dynamic component supported by templates
 * 
 * TODO: Add a database component
 */
@Controller
@SpringBootApplication
public class Main {  
    @Autowired
    ServletContext servletContext;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    /* 
     * https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers
     * 
     * TODO: The second answer here feels more idiomatic, but didn't work.
     * Perhaps after learning more this will become the better option
     * 
     * The pdf seems to be taking a while to load - perhaps it is loading into memory first
     */
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) {
        try {
            // TODO: don't hardcode src/main/resources here
            InputStream inputStream = new FileInputStream("src/main/resources/resume.pdf");
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            response.setContentType("application/pdf");
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
