package co.istad.jbsdemo.spring_security_basic.service;


import co.istad.jbsdemo.spring_security_basic.model.dto.UserRequest;
import co.istad.jbsdemo.spring_security_basic.model.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
