package com.labunco.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MultiplexingUserDetailsService implements UserDetailsService {

    private Map<String, UserDetailsService> resourceDelegatesMap;

    public MultiplexingUserDetailsService(Map<String, UserDetailsService> resourceDelegatesMap) {
        this.resourceDelegatesMap = resourceDelegatesMap;
    }

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] resourceIds = request.getParameterValues("resource_id");
        if (resourceIds == null || resourceIds.length != 1) return null;

        UserDetailsService userDetailsService = resourceDelegatesMap.get(resourceIds[0]);
        if (userDetailsService == null) return null;

        return userDetailsService.loadUserByUsername(username);
    }
}
