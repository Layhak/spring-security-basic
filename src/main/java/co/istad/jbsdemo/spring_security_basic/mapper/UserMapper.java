package co.istad.jbsdemo.spring_security_basic.mapper;

import co.istad.jbsdemo.spring_security_basic.model.Role;
import co.istad.jbsdemo.spring_security_basic.model.User;
import co.istad.jbsdemo.spring_security_basic.model.dto.UserRequest;
import co.istad.jbsdemo.spring_security_basic.model.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "email", source = "request.email"),
            @Mapping(target = "password", source = "request.password"),
            @Mapping(target = "roles", source = "roles")
    })
    User toUser(UserRequest request, Set<Role> roles);

    @Mapping(target = "roles", source = "user.roles", qualifiedByName = "mapRoles")
    UserResponse toUserResponse(User user);

    @Named("mapRoles")
    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

}
