package com.jbatista.ushort.api.services;

import com.jbatista.ushort.api.entities.AdmUser;
import com.jbatista.ushort.api.entities.Configuration;
import com.jbatista.ushort.api.repositories.ConfigurationRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Administration {

    private ConfigurationRepository configurationRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Administration(ConfigurationRepository configurationRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.configurationRepository = configurationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        if (!configurationRepository.existsById("1")) {
            configurationRepository.save(new Configuration("admin", bCryptPasswordEncoder.encode("admin")));
        }
    }

    public Configuration getConfiguration() {
        return configurationRepository.findById("1").get();
    }

    public void saveConfiguration(Configuration newConfiguration) {
        final Configuration configuration = configurationRepository.findById("1").get();
        configuration.setTtlHours(configuration.getTtlHours());

        configurationRepository.save(configuration);
    }

    public AdmUser getAdmUser() {
        final AdmUser admUser = new AdmUser();
        admUser.setUsetname(configurationRepository.findById("1").get().getAdmUser());

        return admUser;
    }

    public void saveAdmUser(AdmUser admUser) {
        final Configuration configuration = configurationRepository.findById("1").get();
        configuration.setAdmUser(admUser.getUsetname());
        configuration.setAdmPassword(bCryptPasswordEncoder.encode(admUser.getPassword()));

        configurationRepository.save(configuration);
    }

}
