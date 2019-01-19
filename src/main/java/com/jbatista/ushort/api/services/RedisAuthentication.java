package com.jbatista.ushort.api.services;

import com.jbatista.ushort.api.entities.Configuration;
import com.jbatista.ushort.api.repositories.ConfigurationRepository;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RedisAuthentication implements AuthenticationProvider {

    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // role used just to pass authorization
    private static final Collection<GrantedAuthority> mockRole = Collections.singletonList(new SimpleGrantedAuthority("ADM"));

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final Configuration configuration = configurationRepository.findById("1").get();

        if ((authentication.getPrincipal() != null)
                && (authentication.getCredentials() != null)
                && configuration.getAdmUser().equals(authentication.getPrincipal())
                && bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), configuration.getAdmPassword())) {

            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), mockRole);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
