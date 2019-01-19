package com.jbatista.ushort.api.repositories;

import com.jbatista.ushort.api.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

}
