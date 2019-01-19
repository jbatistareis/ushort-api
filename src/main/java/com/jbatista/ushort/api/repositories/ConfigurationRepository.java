package com.jbatista.ushort.api.repositories;

import com.jbatista.ushort.api.entities.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
