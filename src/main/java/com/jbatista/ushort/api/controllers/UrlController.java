package com.jbatista.ushort.api.controllers;

import com.jbatista.ushort.api.entities.Address;
import com.jbatista.ushort.api.entities.Stats;
import com.jbatista.ushort.api.services.UrlProcessor;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    @Autowired
    private UrlProcessor urlProcessor;

    @RequestMapping(path = "/{shortUrl}", method = RequestMethod.GET)
    public RedirectView access(@PathVariable String shortUrl) {
        return new RedirectView(urlProcessor.getFull(shortUrl), false);
    }

    @RequestMapping(path = "/api/shorten", method = RequestMethod.GET)
    public Address shorten(@RequestParam String url) {
        return urlProcessor.process(url);
    }

    @RequestMapping(path = "/api/stats", method = RequestMethod.GET)
    public Stats getStats() {
        return urlProcessor.getStats();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity shortcutNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("The requested short URL was not in our database");
    }

}
