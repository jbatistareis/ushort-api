package com.jbatista.ushort.api.services;

import com.jbatista.ushort.api.entities.Address;
import com.jbatista.ushort.api.entities.Configuration;
import com.jbatista.ushort.api.repositories.AddressRepository;
import com.jbatista.ushort.api.repositories.ConfigurationRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlManager {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    public Address process(String url) {
        final Configuration configuration = configurationRepository.findById("1").get();

        final Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.HOUR, configuration.getTtlHours());

        final Address address = new Address(shorten(url), url, (configuration.getTtlHours() > 0) ? expiration.getTime() : null);
        addressRepository.save(address);

        if (configuration.getTtlHours() > 0) {
            redisTemplate.expireAt(address.getId(), address.getExpires());
        }

        return address;
    }

    public String shorten(String url) {
        return url;
    }

}
