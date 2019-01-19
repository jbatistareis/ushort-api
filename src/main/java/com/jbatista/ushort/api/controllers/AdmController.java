package com.jbatista.ushort.api.controllers;

import com.jbatista.ushort.api.entities.AddressPage;
import com.jbatista.ushort.api.entities.AdmUser;
import com.jbatista.ushort.api.entities.Configuration;
import com.jbatista.ushort.api.repositories.AddressRepository;
import com.jbatista.ushort.api.services.Administration;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmController {

    @Autowired
    private Administration administration;
    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(path = "/api/admin/config", method = RequestMethod.GET)
    public Configuration getConfig() {
        return administration.getConfiguration();
    }

    @RequestMapping(path = "/api/admin/config", method = RequestMethod.POST)
    public void saveConfig(@RequestBody Configuration configuration) {
        administration.saveConfiguration(configuration);
    }

    /*
    @RequestMapping(path = "/api/admin/user", method = RequestMethod.GET)
    public AdmUser getUser() {
        return administration.getAdmUser();
    }

    @RequestMapping(path = "/api/admin/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody AdmUser admUser) {
        administration.saveAdmUser(admUser);
    }
     */
    @RequestMapping(path = "/api/admin/url", method = RequestMethod.GET)
    public AddressPage listUrls(@RequestParam int page, @RequestParam int size) {
        return new AddressPage(addressRepository.findAllByOrderByFullUrl(PageRequest.of(page, size)));
    }

    @RequestMapping(path = "/api/admin/url/search", method = RequestMethod.GET)
    public AddressPage searchUrls(@RequestParam String query) {
        return new AddressPage(addressRepository.findAllByOrderByFullUrl()
                .filter(address -> address.getFullUrl().contains(query))
                .get().collect(Collectors.toList()));
    }

    @RequestMapping(path = "/api/admin/url/{url}", method = RequestMethod.DELETE)
    public void deleteUrl(@PathVariable String url) {
        addressRepository.deleteById(url);
    }

}
