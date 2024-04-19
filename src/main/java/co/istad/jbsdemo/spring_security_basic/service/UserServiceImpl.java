package co.istad.jbsdemo.spring_security_basic.service;

import co.istad.jbsdemo.spring_security_basic.mapper.UserMapper;
import co.istad.jbsdemo.spring_security_basic.model.Role;
import co.istad.jbsdemo.spring_security_basic.model.User;
import co.istad.jbsdemo.spring_security_basic.model.dto.UserRequest;
import co.istad.jbsdemo.spring_security_basic.model.dto.UserResponse;
import co.istad.jbsdemo.spring_security_basic.repository.AuthorityRepository;
import co.istad.jbsdemo.spring_security_basic.repository.RoleRepository;
import co.istad.jbsdemo.spring_security_basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ALready Exist");
        }
        Set<Role> roles = new HashSet<>();
        request.roles().forEach(
                role -> {
                    var roleobj = roleRepository.findByName(role).orElseThrow(() -> new NoSuchElementException("Role can't be found by " + role));
                    roles.add(roleobj);
                }
        );
        User newUser = userMapper.toUser(request, roles);
        newUser.setPassword(new BCryptPasswordEncoder().encode(request.password()));
        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }
}
