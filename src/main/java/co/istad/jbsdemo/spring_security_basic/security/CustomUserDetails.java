package co.istad.jbsdemo.spring_security_basic.security;

import co.istad.jbsdemo.spring_security_basic.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    //?add field with domain (User)
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return (Collection<? extends GrantedAuthority>) user.getRoles();
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isCredentialExpired();
    }

    @Override
    public boolean isEnabled() {
        return !user.isDisabled();
    }
}
