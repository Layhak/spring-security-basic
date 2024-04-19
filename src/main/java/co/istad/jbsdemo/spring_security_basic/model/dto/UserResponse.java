package co.istad.jbsdemo.spring_security_basic.model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse(
        String id,
        String email,
        Set<String> roles
        )
{
}
