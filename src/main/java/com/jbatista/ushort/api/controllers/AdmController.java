package com.jbatista.ushort.api.controllers;

import com.jbatista.ushort.api.entities.AdmUser;
import com.jbatista.ushort.api.entities.Configuration;
import com.jbatista.ushort.api.services.Administration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmController {

    @Autowired
    private Administration administration;

    @RequestMapping(path = "/admin/config", method = RequestMethod.GET)
    public Configuration getConfig() {
        return administration.getConfiguration();
    }

    @RequestMapping(path = "/admin/config", method = RequestMethod.POST)
    public void saveConfig(@RequestBody Configuration configuration) {
        administration.saveConfiguration(configuration);
    }

    @RequestMapping(path = "/admin/user", method = RequestMethod.GET)
    public AdmUser getUser() {
        return administration.getAdmUser();
    }

    @RequestMapping(path = "/admin/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody AdmUser admUser) {
        administration.saveAdmUser(admUser);
    }

}
