package co.istad.jbsdemo.spring_security_basic.model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserRequest(
        String email,
        String password,
        Set<String> roles
) {
}
