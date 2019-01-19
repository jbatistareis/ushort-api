package com.jbatista.ushort.api.controllers;

import com.jbatista.ushort.api.entities.Address;
import com.jbatista.ushort.api.services.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    @Autowired
    private UrlManager urlManager;

    @RequestMapping(path = "/{shortUrl}", method = RequestMethod.GET)
    public RedirectView access(@PathVariable String shortUrl) {
        return new RedirectView(urlManager.getFull(shortUrl), false);
    }

    @RequestMapping(path = "/api/shorten", method = RequestMethod.GET)
    public Address shorten(@RequestParam String url) {
        return urlManager.process(url);
    }

}
