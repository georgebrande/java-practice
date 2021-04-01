package com.doctor.appointment.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private static List<String> userList =Arrays.asList("gxg@cst.ro");

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName().trim();

        if (userList.contains(username)) {

            UserDetails userDetails = UserPrinciple.build(username);
            Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            return newAuth;
        } return null;
    }
}
