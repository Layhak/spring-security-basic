package co.istad.jbsdemo.spring_security_basic.restcontroller;

import co.istad.jbsdemo.spring_security_basic.model.dto.UserRequest;
import co.istad.jbsdemo.spring_security_basic.model.dto.UserResponse;
import co.istad.jbsdemo.spring_security_basic.service.UserService;
import co.istad.jbsdemo.spring_security_basic.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse<UserResponse> createNewUser(@RequestBody UserRequest request) {
        return  BaseResponse.<UserResponse>createSuccess().setPayload(userService.createUser(request));
    }
}
