package project.webshop.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.service.user.AuthService;

@Component
@Transactional
public class AuthServiceImpl implements AuthService {
    // value
    // tokenHeader
    // auth manager
    // jwtUtils
    @Value("${token.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    // autowired
    // userDetailService

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public String createToken(@NonNull String username, @NonNull String password,@NonNull Device device) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtils.generateToken(userDetails,device);
    }
}
