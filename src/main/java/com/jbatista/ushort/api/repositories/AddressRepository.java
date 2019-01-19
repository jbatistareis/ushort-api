package com.jbatista.ushort.api.repositories;

import com.jbatista.ushort.api.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    public Streamable<Address> findAllByOrderByFullUrl();

    public Page<Address> findAllByOrderByFullUrl(Pageable pageable);

}
