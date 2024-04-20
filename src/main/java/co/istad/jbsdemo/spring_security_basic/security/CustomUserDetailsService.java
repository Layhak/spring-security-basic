package co.istad.jbsdemo.spring_security_basic.security;

import co.istad.jbsdemo.spring_security_basic.model.User;
import co.istad.jbsdemo.spring_security_basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //find the user by email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with emai:" + email));
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);
        return customUserDetails;
    }
}
